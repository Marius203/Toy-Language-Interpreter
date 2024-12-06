package Model.statements;

import Model.exceptions.MyException;
import Model.expressions.IExpression;
import Model.state.PrgState;
import Model.values.BoolValue;
import Model.values.IValue;


public class IfStmt implements IStmt {
    IExpression exp;
    IStmt thenS;
    IStmt elseS;
    
    public IfStmt(IExpression exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }
    
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue cond = exp.eval(state.getSymTable(), state.getHeap());
        if (cond instanceof BoolValue) {
            boolean boolCond = ((BoolValue) cond).getVal();
            state.getExeStack().push(boolCond ? thenS : elseS);
        } else {
            throw new MyException("Condition is not boolean");
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ") ELSE(" + elseS.toString() + "))";
    }
}


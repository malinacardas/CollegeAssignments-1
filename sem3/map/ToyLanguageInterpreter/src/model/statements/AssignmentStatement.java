package model.statements;

import model.ProgramState;
import model.expressions.Expression;
import utils.SymbolTable;
import utils.exceptions.InterpreterException;

/**
 * Created by mirko on 12/10/2016.
 */
public class AssignmentStatement implements Statement {
    private String variable;
    private Expression value;

    public AssignmentStatement(String variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws InterpreterException {
        SymbolTable<String, Integer> symbolTable = programState.getSymbolTable();
        symbolTable.add(variable, value.evaluate(symbolTable));
        return programState;
    }

    @Override
    public String toString() {
        return variable + " = " + value;
    }
}
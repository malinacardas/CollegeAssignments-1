package model.statements;

import model.ProgramState;
import model.expressions.Expression;
import utils.Heap;
import utils.SymbolTable;
import utils.exceptions.InterpreterException;

/**
 * Created by mirko on 06/12/2016.
 */
public class WriteHeapStatement implements Statement {
    private String variable;
    private Expression value;

    public WriteHeapStatement(String variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws InterpreterException {
        SymbolTable<String, Integer> symbolTable = programState.getSymbolTable();
        Heap<Integer, Integer> heap = programState.getHeap();
        Integer address = symbolTable.getValue(variable);
        heap.add(address, value.evaluate(symbolTable, heap));
        return null;
    }

    @Override
    public String toString() {
        return "writeHeap(" + variable + ", " + value + ")";
    }
}

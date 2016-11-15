package controller;

import model.ProgramState;
import model.statements.Statement;
import repository.Repository;
import utils.*;
import utils.exceptions.InterpreterException;

import java.io.BufferedReader;

/**
 * Created by mirko on 12/10/2016.
 */
public class Controller {
    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void addProgram(Statement statement) {
        ExecutionStack<Statement> executionStack = new ExecutionStackImpl<>();
        SymbolTable<String, Integer> symbolTable = new SymbolTableImpl<>();
        FileTable<Integer, FileData<String, BufferedReader>> fileTable = new FileTableImpl<>();
        FileDescriptorGenerator generator = new FileDescriptorGeneratorImpl();
        Output<String> output = new OutputImpl<String>();

        executionStack.push(statement);

        ProgramState program = new ProgramState(executionStack, symbolTable, fileTable, generator, output);
        repository.add(program);
    }

    public ProgramState getCurrentProgram() throws InterpreterException {
        return repository.getCurrentProgramState();
    }

    public ProgramState executeOneStep(ProgramState program) throws InterpreterException {
        if (program == null) throw new InterpreterException("error: no program to execute");

        ExecutionStack<Statement> executionStack = program.getExecutionStack();
        Statement statement = executionStack.pop();
        if (statement == null) throw new InterpreterException("\nerror: execution stack is empty");
        return statement.execute(program);
    }

    public void executeAllSteps() throws InterpreterException {
        ProgramState currentProgram = repository.getCurrentProgramState();
        while (!currentProgram.getExecutionStack().isEmpty()) {
            executeOneStep(currentProgram);
            repository.logCurrentProgramState();
        }
    }

    public String currentProgramToString() throws InterpreterException {
        ProgramState currentProgram = repository.getCurrentProgramState();
        return currentProgram.toString();
    }
}
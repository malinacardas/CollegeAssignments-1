package repository;

import model.ProgramState;
import utils.exceptions.InterpreterException;

/**
 * Created by mirko on 12/10/2016.
 */
public interface Repository {
    void add(ProgramState programState);

    ProgramState getCurrentProgramState() throws InterpreterException;

    void logCurrentProgramState() throws InterpreterException;
}
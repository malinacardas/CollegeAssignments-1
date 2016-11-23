package view.commands;

import controller.Controller;
import utils.exceptions.InterpreterException;

/**
 * Created by mirko on 14/11/2016.
 */
public class ExecuteAllStepsCommand extends Command {
    private Controller controller;

    public ExecuteAllStepsCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.executeAllSteps();
            System.out.println(controller.currentProgramToString());
            System.out.flush();
        } catch (InterpreterException error) {
            System.out.println(error.toString());
            System.out.flush();
        }
    }
}

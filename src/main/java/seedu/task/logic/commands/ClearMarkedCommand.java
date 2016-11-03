package seedu.task.logic.commands;

import seedu.task.alerts.ClearCommandAlert;
import seedu.task.model.TaskManager;

/**
 * Clears the task manager.
 */

public class ClearMarkedCommand extends Command {

    public static final String COMMAND_WORD = "clearM";
    public static final String MESSAGE_SUCCESS = "Marked Tasks has been cleared!";
    public static final String MESSAGE_FAILURE = "Marked Tasks has not been cleared";
    public static final String MESSAGE_NO_TASKS = "0 tasks listed!";

    public ClearMarkedCommand() {
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        if (ClearCommandAlert.clearCommand()) {
            model.resetData(TaskManager.getEmptyMarkedTaskManager(model.getTaskManager()));
            return new CommandResult(MESSAGE_SUCCESS);
        }
        else
            return new CommandResult(MESSAGE_FAILURE);

    }
}

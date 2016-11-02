package seedu.task.logic.commands;

import seedu.task.alerts.ClearCommandAlert;
import seedu.task.model.TaskManager;

/**
 * Clears the task manager.
 */

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task Manager has been cleared!";
    public static final String MESSAGE_FAILURE = "Task Manager has not been cleared";
    public static final String MESSAGE_NO_TASKS = "0 tasks listed!";

    public ClearCommand() {
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        if (ClearCommandAlert.clearCommand()) {
            model.resetData(TaskManager.getEmptyTaskManager());
            return new CommandResult(MESSAGE_SUCCESS);
        }
        else
            return new CommandResult(MESSAGE_FAILURE);

    }
}

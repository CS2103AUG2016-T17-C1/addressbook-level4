package seedu.task.logic.commands;

import seedu.task.alerts.ClearCommandAlert;
import seedu.task.model.TaskManager;

/**
 * Clears the task manager.
 */

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    
    //@@author A0127720M
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all entries from the to-do list.\n"
            + "Parameters: not required.\n"
            + "Example: " + COMMAND_WORD + "\n"
    		+ "User will be asked to confirm 'clear' action before proceeding. "
    //@@author A0152952A
    		+ "Can be reversed with undo command.";
    //@@author
    
    public static final String MESSAGE_SUCCESS = "Pending tasks have been cleared!";
    public static final String MESSAGE_FAILURE = "Pending tasks has not been cleared";
    public static final String MESSAGE_NO_TASKS = "0 tasks listed!";

    public ClearCommand() {
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        if (ClearCommandAlert.clearCommand()) {
            model.resetData(TaskManager.getEmptyTaskManager(model.getTaskManager()));
            return new CommandResult(MESSAGE_SUCCESS);
        }
        else
            return new CommandResult(MESSAGE_FAILURE);

    }
}

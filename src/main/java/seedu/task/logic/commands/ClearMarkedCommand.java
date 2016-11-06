package seedu.task.logic.commands;

import seedu.task.alerts.ClearCommandAlert;
import seedu.task.model.TaskManager;

/**
 * Clears the task manager.
 */

public class ClearMarkedCommand extends Command {

    public static final String COMMAND_WORD = "clearM";
    
  //@@author A0127720M
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all entries from the marked-tasks list.\n"
            + "Parameters: not required.\n"
            + "Example: " + COMMAND_WORD + "\n"
    		+ "User will be asked to confirm 'clear' action before proceeding. "
    //@@author A0152952A
    		+ "Can be reversed with undo command.\n";
    //@@author
    
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

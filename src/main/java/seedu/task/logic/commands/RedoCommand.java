package seedu.task.logic.commands;


/**
 * Lists all tasks in the task manager to the user.
 */
//@@author A0142360U
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String SHORTCUT = "r";
    public static final String MESSAGE_SUCCESS = "Your Undo command has been reversed.";
    public static final String MESSAGE_FAIL = "No more Undo command can be reversed.";


    public RedoCommand() {}

    @Override
    public CommandResult execute() {
        boolean success = model.redoTask();
        if (success)
            return new CommandResult(MESSAGE_SUCCESS);
        return new CommandResult(MESSAGE_FAIL);
    }
}

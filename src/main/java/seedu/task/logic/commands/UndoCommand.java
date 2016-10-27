package seedu.task.logic.commands;


/**
 * Lists all tasks in the task manager to the user.
 */
//@@author A0142360U
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Your changes are undone";
    public static final String MESSAGE_FAIL = "No more changes can be undone";


    public UndoCommand() {}

    @Override
    public CommandResult execute() {
        boolean success = model.undoTask();
        if (success)
            return new CommandResult(MESSAGE_SUCCESS);
        return new CommandResult(MESSAGE_FAIL);
    }
}

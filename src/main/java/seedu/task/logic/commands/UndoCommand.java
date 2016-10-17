package seedu.task.logic.commands;


/**
 * Lists all tasks in the address book to the user.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Your changes are undone";

    public UndoCommand() {}

    @Override
    public CommandResult execute() {
        model.undoTask();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

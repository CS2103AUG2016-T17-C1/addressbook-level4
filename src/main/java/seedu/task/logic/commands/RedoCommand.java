package seedu.task.logic.commands;


/**
 * Lists all tasks in the task manager to the user.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "redo successful";
    public static final String MESSAGE_FAIL = "redo unsuccessful";


    public RedoCommand() {}

    @Override
    public CommandResult execute() {
        boolean success = model.redoTask();
        if (success)
            return new CommandResult(MESSAGE_SUCCESS);
        return new CommandResult(MESSAGE_FAIL);
    }
}

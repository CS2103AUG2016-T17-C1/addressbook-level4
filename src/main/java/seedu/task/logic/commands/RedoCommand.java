package seedu.task.logic.commands;

/**
 * Lists all tasks in the task manager to the user.
 */
// @@author A0142360U
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String SHORTCUT = "r";
    public static final String MESSAGE_SUCCESS = "Your Undo command has been reversed.";
    public static final String MESSAGE_FAIL = "No more Undo command can be reversed.";

    // @@author A0127720M
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reverse undo command. \n"
            + "Parameters: not required.\n"   + "Example: " + COMMAND_WORD + "\n"
            + "The Redo command can only be executed after Undo. If any other command makes changes, Redo is no longer available."+ "Hotkey: " + SHORTCUT;

    // @@author A0142360U
    public RedoCommand() {
    }

    @Override
    public CommandResult execute() {
        boolean success = model.redoTask();
        if (success)
            return new CommandResult(MESSAGE_SUCCESS);
        return new CommandResult(MESSAGE_FAIL);
    }
}

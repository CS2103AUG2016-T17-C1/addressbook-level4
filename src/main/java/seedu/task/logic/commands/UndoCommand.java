package seedu.task.logic.commands;

/**
 * Lists all tasks in the task manager to the user.
 */
// @@author A0142360U
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String SHORTCUT = "u";
    public static final String MESSAGE_SUCCESS = "Undo %1$s time(s).";
    public static final String MESSAGE_FAIL = "No more changes can be undone.";
    public static final int DEFAULT_NUMBER_OF_UNDO = 1;

    // @@author A0127720M
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reverts previous command. \n"
            + "Parameters: not required.\n" + "Example: " + COMMAND_WORD + "\n"
    // @@author A0152952A 
            + "The Undo command can be executed as many times as needed to the point of application launch. "+ "Hotkey: " + SHORTCUT;

    // @@author A0142360U
    
    // @@author A0139284X
    public static int numberOfUndo;

    public UndoCommand(Integer numberOfUndo) {
        this.numberOfUndo = numberOfUndo;
    }

    @Override
    public CommandResult execute() {
        int numberOfUndoDone = 0;
        
        for (int i=0; i<numberOfUndo; i++) {
            if (model.undoTask()) {
                numberOfUndoDone++;
            } else break;            
        }

        if (numberOfUndoDone > 0)
            return new CommandResult(String.format(MESSAGE_SUCCESS, numberOfUndoDone));
        return new CommandResult(MESSAGE_FAIL);
    }
}

package seedu.task.logic.commands;

/**
 * Lists all tasks in the task manager to the user.
 */
// @@author A0142360U
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String SHORTCUT = "r";
    public static final String MESSAGE_SUCCESS = "Redo %1$s time(s).";
    public static final String MESSAGE_FAIL = "No more Undo command can be reversed.";
    public static final int DEFAULT_NUMBER_OF_REDO = 1;

    // @@author A0127720M
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reverse undo command. \n"
            + "Parameters: not required.\n"   + "Example: " + COMMAND_WORD + "\n"
     // @@author A0152952A      
            + "The Redo command can only be executed after Undo. If any other command makes changes, Redo is no longer available."+ "Hotkey: " + SHORTCUT;

    
    // @@author A0139284X
    public static int numberOfRedo;

    public RedoCommand(Integer numberOfRedo) {
        this.numberOfRedo = numberOfRedo;
    }
    
    // @@author A0142360U

    @Override
    public CommandResult execute() {
        int numberOfRedoDone = 0;
        
        for (int i=0; i<numberOfRedo; i++) {
            if (model.redoTask()) {
                numberOfRedoDone++;
            } else break;            
        }

        if (numberOfRedoDone > 0)
            return new CommandResult(String.format(MESSAGE_SUCCESS, numberOfRedoDone));
        return new CommandResult(MESSAGE_FAIL);
    }
}
package seedu.task.logic.commands;


/**
 * Lists all tasks in the task manager to the user.
 */
//@@author A0142360U
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    
    //@@author A0127720M
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reverse undo command. \n"
            + "Parameters: not required.\n"
            + "Example: " + COMMAND_WORD +"\n"
    		+ "The Redo command can only be executed immediately after one or more Undo commands. If any command other than Undo makes changes to the task list, the Redo command is no longer available until Undo is executed again.";
    
  //@@author A0142360U

    public static final String MESSAGE_SUCCESS = "Redo Successful";
    public static final String MESSAGE_FAIL = "Redo Unsuccessful";


    public RedoCommand() {}

    @Override
    public CommandResult execute() {
        boolean success = model.redoTask();
        if (success)
            return new CommandResult(MESSAGE_SUCCESS);
        return new CommandResult(MESSAGE_FAIL);
    }
}

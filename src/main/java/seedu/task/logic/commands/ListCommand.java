package seedu.task.logic.commands;


/**
 * Lists all tasks in the task manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String SHORTCUT = "l";

    //@@author A0127720M
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the tasks.\n"
            + "Parameters: not required.\n"
            + "Example: " + COMMAND_WORD
    //@@author
    		+ "Hotkey:  " + SHORTCUT;
    
    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public ListCommand() {}

    @Override
    public CommandResult execute() {
        model.updateFilteredListToShowAll();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

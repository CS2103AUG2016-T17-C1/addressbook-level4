package seedu.task.logic.commands;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.ClearCommand;
import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.DeleteCommand;
import seedu.task.logic.commands.EditCommand;
import seedu.task.logic.commands.ExitCommand;
import seedu.task.logic.commands.FindCommand;
import seedu.task.logic.commands.HelpCommand;
import seedu.task.logic.commands.IncorrectCommand;
import seedu.task.logic.commands.ListCommand;
import seedu.task.logic.commands.MarkCommand;
import seedu.task.logic.commands.ChangeDirectoryCommand;
import seedu.task.logic.commands.SelectCommand;
import seedu.task.logic.commands.UndoCommand;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.events.ui.ShowHelpRequestEvent;

//@@ author A0127720M
/**
 * Format full help instructions for every command for display.
 */

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    // @@author A0152952A
    public static final String SHORTCUT = "h";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
			+ "With no parametres help will open the entire dialog box, otherwise it will give specific details of one command (i.e. add, edit).\n"
            + "Example: " + COMMAND_WORD + "     OR     " + COMMAND_WORD + " " + AddCommand.COMMAND_WORD  + "\n" 
            + "Hotkey: " + SHORTCUT;
    // @@author

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
    private static final Pattern HELP_ARGS_FORMAT = Pattern.compile("(?<arguments>.*)");
    private String args;
    
    public HelpCommand(String args) {
    	this.args = args;
    }

    @Override
    public CommandResult execute() {
		final Matcher matcher = HELP_ARGS_FORMAT.matcher(args.trim());
		if (!matcher.matches()) {
			return new CommandResult(HelpCommand.MESSAGE_USAGE);
		}
		
		switch (matcher.group("arguments")) {
		
		case AddCommand.SHORTCUT:
		case AddCommand.COMMAND_WORD:
			return new CommandResult(AddCommand.MESSAGE_USAGE);
			
		case EditCommand.SHORTCUT:
		case EditCommand.COMMAND_WORD:
			return new CommandResult(EditCommand.MESSAGE_USAGE);
			
		case DeleteCommand.SHORTCUT:
		case DeleteCommand.COMMAND_WORD:
			return new CommandResult(DeleteCommand.MESSAGE_USAGE);
			
		case FindCommand.SHORTCUT:
		case FindCommand.COMMAND_WORD:
			return new CommandResult(FindCommand.MESSAGE_USAGE);
			
		case HelpCommand.SHORTCUT:
		case HelpCommand.COMMAND_WORD:
			return new CommandResult(HelpCommand.MESSAGE_USAGE);
			
		case ListCommand.SHORTCUT:
		case ListCommand.COMMAND_WORD:
			return new CommandResult(ListCommand.MESSAGE_USAGE);
			
		case SelectCommand.SHORTCUT:
		case SelectCommand.COMMAND_WORD:
			return new CommandResult(SelectCommand.MESSAGE_USAGE);
			
		case MarkCommand.SHORTCUT:
		case MarkCommand.COMMAND_WORD:
			return new CommandResult(MarkCommand.MESSAGE_USAGE);
			
		case ClearMarkedCommand.COMMAND_WORD:
			return new CommandResult(ClearMarkedCommand.MESSAGE_USAGE);
			
		case UndoCommand.SHORTCUT:
		case UndoCommand.COMMAND_WORD:
			return new CommandResult(UndoCommand.MESSAGE_USAGE);
			
		case RedoCommand.SHORTCUT:
		case RedoCommand.COMMAND_WORD:
			return new CommandResult(RedoCommand.MESSAGE_USAGE);
			
		case ChangeDirectoryCommand.COMMAND_WORD:
			return new CommandResult(ChangeDirectoryCommand.MESSAGE_USAGE);
			
		case ClearCommand.COMMAND_WORD:
			return new CommandResult(ClearCommand.MESSAGE_USAGE);
			
		case ExitCommand.COMMAND_WORD:
			return new CommandResult(ExitCommand.MESSAGE_USAGE);
			
		default:
			EventsCenter.getInstance().post(new ShowHelpRequestEvent());
			return new CommandResult(SHOWING_HELP_MESSAGE);
    }
   }
}


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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

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
		case AddCommand.COMMAND_WORD:
			return new CommandResult(AddCommand.MESSAGE_USAGE);
		case EditCommand.COMMAND_WORD:
			return new CommandResult(EditCommand.MESSAGE_USAGE);
		case DeleteCommand.COMMAND_WORD:
			return new CommandResult(DeleteCommand.MESSAGE_USAGE);
		case FindCommand.COMMAND_WORD:
			return new CommandResult(FindCommand.MESSAGE_USAGE);
		case ListCommand.COMMAND_WORD:
			return new CommandResult(ListCommand.MESSAGE_USAGE);
		case SelectCommand.COMMAND_WORD:
			return new CommandResult(SelectCommand.MESSAGE_USAGE);
		case MarkCommand.COMMAND_WORD:
			return new CommandResult(MarkCommand.MESSAGE_USAGE);
		case ClearMarkedCommand.COMMAND_WORD:
			return new CommandResult(ClearMarkedCommand.MESSAGE_USAGE);
		case UndoCommand.COMMAND_WORD:
			return new CommandResult(UndoCommand.MESSAGE_USAGE);
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


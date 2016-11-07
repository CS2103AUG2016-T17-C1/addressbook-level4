package seedu.task.alerts;

import java.util.Optional;

import seedu.task.alerts.HelpCommandAlert;
import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.ClearCommand;
import seedu.task.logic.commands.DeleteCommand;
import seedu.task.logic.commands.EditCommand;
import seedu.task.logic.commands.ExitCommand;
import seedu.task.logic.commands.FindCommand;
import seedu.task.logic.commands.HelpCommand;
import seedu.task.logic.commands.ListCommand;
import seedu.task.logic.commands.MarkCommand;
import seedu.task.logic.commands.ChangeDirectoryCommand;
import seedu.task.logic.commands.SelectCommand;
import seedu.task.logic.commands.UndoCommand;
import seedu.task.logic.commands.RedoCommand;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import seedu.task.logic.commands.CommandResult;
import seedu.task.model.TaskManager;

//@@author A0142360U
public class HelpCommandAlert extends Alert {

    public static final String CLEAR_COMMAND_TITLE = "Showing help for all tasks";
    public static final String CLEAR_COMMAND_HEADER_TEXT = "The following commands are available to the user:\n\n"
    		+ AddCommand.COMMAND_WORD + ", "
    		+ ChangeDirectoryCommand.COMMAND_WORD + ", "
    		+ ClearCommand.COMMAND_WORD + ", "
    		+ DeleteCommand.COMMAND_WORD + ", "
    		+ EditCommand.COMMAND_WORD + ", "
    		+ ExitCommand.COMMAND_WORD + ", "
    		+ FindCommand.COMMAND_WORD + ", "
    		+ HelpCommand.COMMAND_WORD + ", "
    		+ ListCommand.COMMAND_WORD + ", "
    		+ MarkCommand.COMMAND_WORD + ", "
    		+ RedoCommand.COMMAND_WORD + ", "
    		+ SelectCommand.COMMAND_WORD + ", "
    		+ UndoCommand.COMMAND_WORD + "\n\n"
    		+ "For detailed instructions about each command, enter: help COMMAND\n"
    		+ "Shorthand commands are also available for commonly used commands and are specifid in the instructions.";

    public HelpCommandAlert(AlertType alertType) {
        super(alertType);
    }

    public static boolean helpCommand() {

        try {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(CLEAR_COMMAND_TITLE);
            alert.setHeaderText(CLEAR_COMMAND_HEADER_TEXT);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        } catch (ExceptionInInitializerError | IllegalStateException e) {
            return true;
        }

    }

}

package seedu.task.logic.commands;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import seedu.task.model.TaskManager;

/**
 * Clears the task manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task Manager has been cleared!";
    public static final String MESSAGE_FAILURE = "Task Manager has not been cleared";

    public ClearCommand() {}


    @Override
    public CommandResult execute() {
        assert model != null;
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Clear all tasks");
        alert.setHeaderText("Are you sure you want to clear ALL TASKS in the task manager?");
        alert.setContentText("NOTE: This cannot be undone! ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            model.resetData(TaskManager.getEmptyTaskManager());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }

    }
}

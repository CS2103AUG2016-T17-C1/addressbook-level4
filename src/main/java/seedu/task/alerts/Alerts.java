package seedu.task.alerts;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import seedu.task.logic.commands.CommandResult;
import seedu.task.model.TaskManager;

//@@author A0142360U
public class Alerts extends Alert {

    public static final String CLEAR_COMMAND_TITLE = "Clear all tasks";
    public static final String CLEAR_COMMAND_HEADER_TEXT = "Are you sure you want to clear all tasks in the task manager?";

    public Alerts(AlertType alertType) {
        super(alertType);
    }

    public static boolean ClearCommandAlert(){

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
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

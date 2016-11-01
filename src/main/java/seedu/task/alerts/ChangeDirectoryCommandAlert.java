package seedu.task.alerts;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
//@@author A0142360U
public class ChangeDirectoryCommandAlert extends Alert{

    public static final String CHANGE_DIRECTORY_COMMAND_TITLE = "Change task directory";
    public static final String CHANGE_DIRECTORY_COMMAND_HEADER_TEXT = "Task directory has been changed successfully, Never Forget must now restart.\nPlease launch the application again";

    public ChangeDirectoryCommandAlert(AlertType alertType) {
        super(alertType);
    }

    public static boolean changeDirectoryCommand() {

        try {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(CHANGE_DIRECTORY_COMMAND_TITLE);
            alert.setHeaderText(CHANGE_DIRECTORY_COMMAND_HEADER_TEXT);
            alert.showAndWait();
            if (!alert.isShowing()) {
                return true;
            }
            else {
                return false;
            }

        } catch (ExceptionInInitializerError | IllegalStateException e) {
            return false;
        }

    }



}

package seedu.task.alerts;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
//@@author A0142360U
public class TransferTaskAlert extends Alert {

    public static final String CHANGE_DIRECTORY_COMMAND_TITLE = "Change of Task Storage Directory";
    public static final String CHANGE_DIRECTORY_HEADER_TEXT = "Would you like your existing tasks to be transferred over to the new directory?";
    public static final ButtonType buttonTypeYes = new ButtonType("Yes");
    public static final ButtonType buttonTypeNo = new ButtonType("No");
    public static final ButtonType buttonTypeCancel = new ButtonType("Cancel");
    public static final String yes = "Yes";
    public static final String no = "No";
    public static final String noAction = "noAction";

    public TransferTaskAlert(AlertType alertType) {
        super(alertType);
    }

    public static String transferTaskOptionAlert() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle(CHANGE_DIRECTORY_COMMAND_TITLE);
            alert.setHeaderText(CHANGE_DIRECTORY_HEADER_TEXT);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo,buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                return yes;
            } else if (result.get() == buttonTypeNo){
                return no;
            }
            else {
                return noAction;
            }


        } catch (ExceptionInInitializerError | IllegalStateException e) {
            return noAction;
        }

    }

}

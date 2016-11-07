package seedu.task.ui;

import javafx.fxml.FXML;
import seedu.task.ui.CheckTaskAttributes;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import seedu.task.model.task.ReadOnlyTask;

public class TaskCard extends UiPart {

    private static final String FXML = "TaskListCard.fxml";
    public static final String EMPTY_TASK_OBJECT_STRING = "";
    public static final String HIGHEST_IMPORTANCE_LEVEL = "***";
    public static final String MEDIUM_IMPORTANCE_LEVEL = "**";
    public static final String LOWEST_IMPORTANCE_LEVEL = "*";
    public int taskCardDateTimeStatus;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label dueDate;
    @FXML
    private Label importance;
    @FXML
    private Label dueTime;
    @FXML
    private Label tags;
    @FXML
    private ImageView firstStar;
    @FXML
    private ImageView secondStar;
    @FXML
    private ImageView thirdStar;

    private ReadOnlyTask task;
    private int displayedIndex;

    public TaskCard() {

    }

    public static TaskCard load(ReadOnlyTask task, int displayedIndex) {
        TaskCard card = new TaskCard();
        card.task = task;
        card.displayedIndex = displayedIndex;

        return UiPartLoader.loadUiPart(card);

    }

    // @@author A0142360U
    @FXML
    public void initialize() {

        CheckTaskAttributes checkTask = new CheckTaskAttributes(task);

        firstStar.setVisible(false);
        secondStar.setVisible(false);
        thirdStar.setVisible(false);

        name.setText("Task: " + task.getName().fullName);
        id.setText(displayedIndex + ". ");

        displayAppropriateTaskAttributes(checkTask);
        setTaskCardColor(checkTask);

    }

    /*
     * Checks if task has a start date or an end date and displays the
     * appropriate text output on the card.
     */
    public void displayAppropriateTaskAttributes(CheckTaskAttributes checkTask) {
        if (checkTask.startDateExists() && checkTask.endDateExists()) {
            dueDate.setManaged(true);
            dueDate.setText("Starts on " + task.getEventStart().getStartDate().toString() + " and to be completed by "
                    + task.getDeadline().getDueDate().toString());

        } else if (checkTask.endDateExists()) {
            dueDate.setManaged(true);
            dueDate.setText("Task to be completed by Date: " + task.getDeadline().getDueDate().toString());

        } else if (checkTask.startDateExists()) {
            dueDate.setManaged(true);
            dueDate.setText("Task to start on Date: " + task.getEventStart().getStartDate().toString());

        } else
            dueDate.setManaged(false);

        // Checks if task has a start or an end time and displays the
        // appropriate text output on the card
        if (checkTask.startTimeExists() && checkTask.endTimeExists()) {
            dueTime.setManaged(true);
            dueTime.setText("Starts at time " + task.getEventStart().getStartTime().getTime() + " and ends at time "
                    + task.getDeadline().getDueTime().getTime() + "hours");
        }

        else if (checkTask.endTimeExists()) {
            dueTime.setManaged(true);
            dueTime.setText("Ends at time " + task.getDeadline().getDueTime().getTime() + "hours");
        }

        else if (checkTask.startTimeExists()) {
            dueTime.setManaged(true);
            dueTime.setText("Starts at time " + task.getEventStart().getStartTime().getTime() + "hours");
        }

        else
            dueTime.setManaged(false);

        // Checks if the task's importance level has been set and displays the
        // appropriate text output on the card
        if (!task.getImportance().value.toString().equals(EMPTY_TASK_OBJECT_STRING)) {
            importance.setManaged(true);
            importance.setText("Importance: " + task.getImportance().value);
            checkImportanceLevel();
        } else
            importance.setManaged(false);

        tags.setText(task.tagsString());

    }

    /*
     * If start date/time of event task has passed, display green. However, if
     * end date/time has also passed, display red.
     */
    public void setTaskCardColor(CheckTaskAttributes checkTask) {
        if (checkifDeadlinePassed(checkTask)) {
            cardPane.setStyle("-fx-background-color: #E91E63;");
        } else if (checkifEventStarted(checkTask)) {
            cardPane.setStyle("-fx-background-color: #64FFDA;");
        }
    }

    public boolean checkifEventStarted(CheckTaskAttributes checkTask) {
        if (checkTask.startDateExists() && checkTask.startTimeExists()) {
            return checkTask.checkIfDateTimeHasPassed(
                    task.getEventStart().getStartDate().toString() + task.getEventStart().getStartTime());
        } else if (checkTask.startDateExists()) {
            return checkTask.checkIfDateHasPassed(task.getEventStart().getStartDate().toString());
        }

        else {
            return false;
        }
    }

    public boolean checkifDeadlinePassed(CheckTaskAttributes checkTask) {
        if (checkTask.endDateExists() && checkTask.endTimeExists()) {
            return checkTask.checkIfDateTimeHasPassed(
                    task.getDeadline().getDueDate().toString() + task.getDeadline().getDueTime());
        } else if (checkTask.endDateExists()) {
            return checkTask.checkIfDateHasPassed(task.getDeadline().getDueDate().toString());
        }

        else {
            return false;
        }
    }

    /*
     * Check importance level of the task and assigns the the number of stars
     * displayed in the HBox to distinguish between the most important tasks and
     * the least important tasks;
     */
    public void checkImportanceLevel() {

        if (task.getImportance().value.toString().equals(HIGHEST_IMPORTANCE_LEVEL)) {
            firstStar.setVisible(true);
            secondStar.setVisible(true);
            thirdStar.setVisible(true);

        } else if (task.getImportance().value.toString().equals(MEDIUM_IMPORTANCE_LEVEL)) {
            firstStar.setVisible(true);
            secondStar.setVisible(true);
            thirdStar.setVisible(false);

        } else if (task.getImportance().value.toString().equals(LOWEST_IMPORTANCE_LEVEL)) {
            firstStar.setVisible(true);
            secondStar.setVisible(false);
            thirdStar.setVisible(false);

        }
    }

    public HBox getLayout() {
        return cardPane;
    }

    @Override
    public void setNode(Node node) {
        cardPane = (HBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}

package seedu.task.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.task.model.task.ReadOnlyTask;

public class MarkedTaskCard extends UiPart{

    private static final String FXML = "MarkedTaskListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label completedDate;
    @FXML
    private Label importance;
//    @FXML
//    private Label dueTime;
    @FXML
    private Label tags;

    private ReadOnlyTask task;
    private int displayedIndex;

    public MarkedTaskCard(){

    }

    public static MarkedTaskCard load(ReadOnlyTask task, int displayedIndex){
        MarkedTaskCard card = new MarkedTaskCard();
        card.task = task;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    @FXML
    public void initialize() {
        name.setText("Task: "+ task.getName().fullName);
        id.setText(displayedIndex + ". ");
        completedDate.setText("Completed on: "+ LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        importance.setText("Importance: " + task.getImportance().value);
//        dueTime.setText("To be completed by "+task.getDueTime().value+"hours");
        tags.setText(task.tagsString());
    }

    public HBox getLayout() {
        return cardPane;
    }

    @Override
    public void setNode(Node node) {
        cardPane = (HBox)node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}

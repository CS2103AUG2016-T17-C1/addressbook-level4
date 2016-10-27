package seedu.task.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.task.model.task.ReadOnlyTask;

import java.util.logging.Logger;


//@@author A0127720M

/**
 * Panel containing the list of marked tasks.
 */
public class MarkedTaskListPanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(MarkedTaskListPanel.class);
    private static final String FXML = "MarkedTaskListPanel.fxml";
    private VBox panel;
    private AnchorPane placeHolderPane;

    @FXML
    private ListView<ReadOnlyTask> markedTaskListView;

    public MarkedTaskListPanel() {
        super();
    }

    @Override
    public void setNode(Node node) {
        panel = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Override
    public void setPlaceholder(AnchorPane pane) {
        this.placeHolderPane = pane;
    }

    public static MarkedTaskListPanel load(Stage primaryStage, AnchorPane markedTaskListPlaceholder,
                                       ObservableList<ReadOnlyTask> markedTaskList) {
        MarkedTaskListPanel markedTaskListPanel =
                UiPartLoader.loadUiPart(primaryStage, markedTaskListPlaceholder, new MarkedTaskListPanel());
        markedTaskListPanel.configure(markedTaskList);
        return markedTaskListPanel;
    }

    private void configure(ObservableList<ReadOnlyTask> taskList) {
        setConnections(taskList);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<ReadOnlyTask> taskList) {
        markedTaskListView.setItems(taskList);
        markedTaskListView.setCellFactory(listView -> new MarkedTaskListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        markedTaskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in person list panel changed to : '" + newValue + "'");
                raise(new TaskPanelSelectionChangedEvent(newValue));
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            markedTaskListView.scrollTo(index);
            markedTaskListView.getSelectionModel().clearAndSelect(index);
        });
    }

    class MarkedTaskListViewCell extends ListCell<ReadOnlyTask> {

        public MarkedTaskListViewCell() {
        }

        @Override
        protected void updateItem(ReadOnlyTask task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(MarkedTaskCard.load(task, getIndex() + 1).getLayout());
            }
        }
    }

}

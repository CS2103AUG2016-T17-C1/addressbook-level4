package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.stage.Stage;
import seedu.task.model.task.ReadOnlyTask;

/**
 * Provides a handle to a task card in the task list panel.
 */
public class TaskCardHandle extends GuiHandle {
    private static final String TASK_NAME_FIELD_ID = "#name";
    private static final String DUE_DATE_FIELD_ID = "#dueDate";
    private static final String DUE_TIME_FIELD_ID = "#dueTime";
    private static final String IMPORTANCE_FIELD_ID = "#importance";

    private Node node;

    public TaskCardHandle(GuiRobot guiRobot, Stage primaryStage, Node node){
        super(guiRobot, primaryStage, null);
        this.node = node;
    }

    protected String getTextFromLabel(String fieldId) {
        return getTextFromLabel(fieldId, node);
    }

    public String getFullName() {
        return getTextFromLabel(TASK_NAME_FIELD_ID);
    }

    public String getDueDate() {
        return getTextFromLabel(DUE_DATE_FIELD_ID);
    }

    public String getDueTime() {
        return getTextFromLabel(DUE_TIME_FIELD_ID);
    }

    public String getImportance() {
        return getTextFromLabel(IMPORTANCE_FIELD_ID);
    }

    public boolean isSameTask(ReadOnlyTask task){
//        System.out.println(task.getName().fullName+task.getName()+"fullnamexists");
//        System.out.println(task.getDueTime().value+task.getDueTime()+"fulltimeexists");
//        System.out.println(task.getDueDate().value+task.getDueDate()+"fulldatexists");
//        System.out.println(task.getImportance().value+task.getImportance()+"fullimportanceexists");
        return getFullName().equals(task.getName().fullName) && getDueTime().equals(task.getDeadline().getDueTime().value)
                && getImportance().equals(task.getImportance().value) && getDueDate().equals(task.getDeadline().getDueDate().toString());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TaskCardHandle) {
            TaskCardHandle handle = (TaskCardHandle) obj;
            return getFullName().equals(handle.getFullName())
                    && getDueDate().equals(handle.getDueDate()); //TODO: compare the rest
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getFullName() + " " + getDueDate();
    }
}

package seedu.task.ui;

import seedu.task.model.task.ReadOnlyTask;

//@@author A0142360U
public class CheckTaskAttributes {
    private ReadOnlyTask task;
    public static final String EMPTY_TASK_OBJECT_STRING = "";

    public CheckTaskAttributes(ReadOnlyTask task) {
        this.task = task;
    }

    public boolean startDateExists() {
        if (!this.task.getEventStart().getStartDate().toString().equals(EMPTY_TASK_OBJECT_STRING))
            return true;

        return false;
    }

    public boolean startTimeExists() {
        if (!this.task.getEventStart().getStartTime().toString().equals(EMPTY_TASK_OBJECT_STRING))
            return true;

        return false;
    }

    public boolean endDateExists() {
        if (!this.task.getDeadline().getDueDate().toString().equals(EMPTY_TASK_OBJECT_STRING))
            return true;

        return false;
    }

    public boolean endTimeExists() {
        if (!this.task.getDeadline().getDueTime().toString().equals(EMPTY_TASK_OBJECT_STRING))
            return true;

        return false;
    }

}

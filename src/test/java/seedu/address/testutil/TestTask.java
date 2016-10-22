package seedu.address.testutil;

import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.*;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private TaskName taskName;
    private Importance importance;
    private DeadLine deadLine;
    private UniqueTagList tags;

    public TestTask() {
        tags = new UniqueTagList();
    }

    public void setName(TaskName taskName) {
        this.taskName = taskName;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

//    public void setDueTime(DueTime dueTime) {
//        this.deadLine.setDueTime(dueTime);
//    }
//
//    public void setDueDate(DueDate dueDate) {
//        this.deadLine.setDueDate(dueDate);
//    }

    @Override
    public TaskName getName() {
        return taskName;
    }

//    @Override
//    public DueDate getDueDate() {
//        return dueDate;
//    }
//
//    @Override
//    public DueTime getDueTime() {
//        return dueTime;
//    }

    @Override
    public Importance getImportance() {
        return importance;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName + " ");
        sb.append("d/" + this.getDeadLine().getDueDate().getDueDate() + " ");
        sb.append("e/" + this.getDeadLine().getDueTime().value + " ");
        sb.append("i/" + this.getImportance().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }

    @Override
    public DeadLine getDeadLine() {
        return this.deadLine;
    }

    public void setDeadLine(DeadLine deadLine) {
        this.deadLine = deadLine;
    }
}

package seedu.address.testutil;

import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.*;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private TaskName taskName;
    private Importance importance;
    private Deadline deadline;
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

//    public void setDueTime(Time dueTime) {
//        this.deadLine.setDueTime(dueTime);
//    }
//
//    public void setDueDate(Date dueDate) {
//        this.deadLine.setDueDate(dueDate);
//    }

    @Override
    public TaskName getName() {
        return taskName;
    }

//    @Override
//    public Date getDueDate() {
//        return dueDate;
//    }
//
//    @Override
//    public Time getDueTime() {
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
        sb.append("d/" + this.getDeadLine().getDueDate().toString() + " ");
        sb.append("e/" + this.getDeadLine().getDueTime().value + " ");
        sb.append("i/" + this.getImportance().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }

    @Override
    public Deadline getDeadLine() {
        return this.deadline;
    }

    public void setDeadLine(Deadline deadline) {
        this.deadline = deadline;
    }
}

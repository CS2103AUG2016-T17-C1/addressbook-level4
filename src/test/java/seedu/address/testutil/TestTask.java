package seedu.address.testutil;

import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.*;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private TaskName taskName;
    private Importance importance;
    private DueTime dueTime;
    private DueDate dueDate;
    private UniqueTagList tags;

    public TestTask() {
        tags = new UniqueTagList();
    }

    public void setName(TaskName taskName) {
        this.taskName = taskName;
    }

    public void setAddress(Importance importance) {
        this.importance = importance;
    }

    public void setEmail(DueTime dueTime) {
        this.dueTime = dueTime;
    }

    public void setPhone(DueDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public TaskName getName() {
        return taskName;
    }

    @Override
    public DueDate getPhone() {
        return dueDate;
    }

    @Override
    public DueTime getEmail() {
        return dueTime;
    }

    @Override
    public Importance getAddress() {
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
        sb.append("p/" + this.getPhone().value + " ");
        sb.append("e/" + this.getEmail().value + " ");
        sb.append("a/" + this.getAddress().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
}

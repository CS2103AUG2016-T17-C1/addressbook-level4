package seedu.address.testutil;

import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.*;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private TaskName taskName;
    private Importance importance;
    private EventStart eventStart;
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

    @Override
    public TaskName getName() {
        return taskName;
    }

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
        sb.append("sd/" + this.getEventStart().getStartDate().toString() + " ");
        sb.append("st/" + this.getEventStart().getStartTime().value + " ");
        sb.append("d/" + this.getDeadline().getDueDate().toString() + " ");
        sb.append("e/" + this.getDeadline().getDueTime().value + " ");
        sb.append("i/" + this.getImportance().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }

    @Override
    public Deadline getDeadline() {
        return this.deadline;
    }

    public void setDeadLine(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public EventStart getEventStart() {
        return this.eventStart;
    }

    public void setEventStart(EventStart eventStart) {
        this.eventStart = eventStart;
    }
}

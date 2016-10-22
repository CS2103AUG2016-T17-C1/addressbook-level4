package seedu.task.model.task;

import java.util.Objects;

import seedu.task.commons.util.CollectionUtil;
import seedu.task.model.tag.UniqueTagList;

/**
 * Represents a Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private TaskName taskName;
    private DeadLine deadLine;
    private Importance importance;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName taskName, DeadLine deadLine, Importance importance, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(taskName, deadLine.getDueDate(), deadLine.getDueTime(), importance, tags);
        this.taskName = taskName;
        this.deadLine = deadLine;
        this.importance = importance;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getDeadLine(), source.getImportance(), source.getTags());
    }

    public Task(DeadLine deadLine, Importance importance, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(deadLine.getDueDate(), deadLine.getDueTime(), importance, tags);
        this.deadLine = deadLine;
        this.importance = importance;
        this.tags = new UniqueTagList(tags);
        // TODO Auto-generated constructor stub
    }

    @Override
    public TaskName getName() {
        return taskName;
    }
//
//    @Override
//    public DueDate getDueDate() {
//        return getDeadLine().getDueDate();
//    }
//
//    @Override
//    public DueTime getDueTime() {
//        return getDeadLine().getDueTime();
//    }

    @Override
    public Importance getImportance() {
        return importance;
    }


    public void setName(TaskName name) {
        this.taskName = name;
    }

    public void setDueDate(DueDate dueDate) {
        this.getDeadLine().setDueDate(dueDate);
    }

    public void setDueTime(DueTime dueTime) {
        this.getDeadLine().setDueTime(dueTime);
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName, getDeadLine(), importance, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public DeadLine getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(DeadLine deadLine) {
        this.deadLine = deadLine;
    }

}
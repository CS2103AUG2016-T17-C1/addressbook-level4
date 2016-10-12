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
    private DueDate dueDate;
    private DueTime dueTime;
    private Importance importance;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName taskName, DueDate dueDate, DueTime dueTime, Importance importance, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(taskName, dueDate, dueTime, importance, tags);
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.importance = importance;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getDueDate(), source.getDueTime(), source.getImportance(), source.getTags());
    }

    @Override
    public TaskName getName() {
        return taskName;
    }

    @Override
    public DueDate getDueDate() {
        return dueDate;
    }

    @Override
    public DueTime getDueTime() {
        return dueTime;
    }

    @Override
    public Importance getImportance() {
        return importance;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
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
        return Objects.hash(taskName, dueDate, dueTime, importance, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

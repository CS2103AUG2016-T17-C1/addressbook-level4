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
    private Deadline deadline;
    private Importance importance;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName taskName, Deadline deadline, Importance importance, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(taskName, deadline.getDueDate(), deadline.getDueTime(), importance, tags);
        this.taskName = taskName;
        this.deadline = deadline;
        this.importance = importance;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getDeadLine(), source.getImportance(), source.getTags());
    }

    public Task(Deadline deadline, Importance importance, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(deadline.getDueDate(), deadline.getDueTime(), importance, tags);
        this.deadline = deadline;
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

    public Deadline getDeadLine() {
        return deadline;
    }

    public void setDeadLine(Deadline deadline) {
        this.deadline = deadline;
    }

}
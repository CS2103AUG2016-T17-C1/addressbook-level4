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
    private boolean isTaskCompleted;

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
        this.isTaskCompleted = false;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getDueDate(), source.getDueTime(), source.getImportance(), source.getTags());
    }

    public Task(DueDate dueDate2, DueTime dueTime2, Importance importance2, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(taskName, dueDate, dueTime, importance, tags);
        this.dueDate = dueDate2;
        this.dueTime = dueTime2;
        this.importance = importance2;
        this.tags = new UniqueTagList(tags);
        // TODO Auto-generated constructor stub
    }
    
    public void markAsCompleted() {
    	this.isTaskCompleted = true;
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


    public void setName(TaskName name) {
        this.taskName = name;
    }

    public void setDueDate(DueDate dueDate2) {
        this.dueDate = dueDate2;
    }

    public void setDueTime(DueTime dueTime2) {
        this.dueTime = dueTime2;
    }

    public void setImportance(Importance importance2) {
        this.importance = importance2;
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

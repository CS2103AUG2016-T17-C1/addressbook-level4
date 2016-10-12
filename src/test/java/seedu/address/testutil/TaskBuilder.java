package seedu.address.testutil;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.*;

/**
 *
 */
public class TaskBuilder {

    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    public TaskBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new TaskName(name));
        return this;
    }

    public TaskBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }

    public TaskBuilder withImportance(String importance) throws IllegalValueException {
        this.task.setImportance(new Importance(importance));
        return this;
    }

    public TaskBuilder withDueDate(String dueDate) throws IllegalValueException {
        this.task.setDueDate(new DueDate(dueDate));
        return this;
    }

    public TaskBuilder withDueTime(String dueTime) throws IllegalValueException {
        this.task.setDueTime(new DueTime(dueTime));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}

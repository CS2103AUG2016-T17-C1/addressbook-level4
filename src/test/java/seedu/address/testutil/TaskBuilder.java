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

    public TestTask build() {
        return this.task;
    }

    public TaskBuilder withDeadLine(String dueDate, String dueTime) throws IllegalValueException {
        this.task.setDeadLine(new Deadline(new Date(dueDate), new Time(dueTime)));
        return this;
    }

    public TaskBuilder withEventStart(String startDate, String startTime) throws IllegalValueException {
        this.task.setEventStart(new EventStart(new Date(startDate), new Time(startTime)));
        return this;
    }

}

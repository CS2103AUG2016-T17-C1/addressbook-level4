package seedu.task.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.tag.Tag;
import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.*;

import java.util.ArrayList;
import java.util.List;

/**
 * JAXB-friendly version of the Task.
 */
public class XmlAdaptedTask {

    @XmlElement(required = true)
    private String taskName;
    @XmlElement(required = true)
    private String dueDate;
    @XmlElement(required = true)
    private String dueTime;
    @XmlElement(required = true)
    private String startDate;
    @XmlElement(required = true)
    private String startTime;
    @XmlElement(required = true)
    private String importance;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedTask() {}


    /**
     * Converts a given Task into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedTask
     */
    public XmlAdaptedTask(ReadOnlyTask source) {
        taskName = source.getName().fullName;
        dueDate = source.getDeadline().getDueDate().toString();
        dueTime = source.getDeadline().getDueTime().getTime();
        startDate = source.getEventStart().getStartDate().toString();
        startTime = source.getEventStart().getStartTime().getTime();
        importance = source.getImportance().value;
        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Task object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }
        final TaskName taskName = new TaskName(this.taskName);
        final Date dueDate = new Date(this.dueDate);
        final Time dueTime = new Time(this.dueTime);
        final Date startDate = new Date (this.startDate);
        final Time startTime = new Time (this.startTime);
        final Deadline deadline = new Deadline(dueDate, dueTime);
        final EventStart eventStart = new EventStart (startDate,startTime);
        final Importance importance = new Importance(this.importance);
        final UniqueTagList tags = new UniqueTagList(taskTags);
        return new Task(taskName,eventStart, deadline, importance, tags);
    }
}

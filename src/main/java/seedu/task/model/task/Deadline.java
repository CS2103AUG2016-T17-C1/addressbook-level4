package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

//@@author A0139284X
public class Deadline {

    public static final String MESSAGE_DEADLINE_CONSTRAINTS = "Task's deadline cannot have due time only";

    private Date date;
    private Time time;

    public Deadline(Date date, Time time) throws IllegalValueException {
        if (date.getDate().isEmpty() && !(time.toString().isEmpty())) {
            throw new IllegalValueException(MESSAGE_DEADLINE_CONSTRAINTS);
        }
        this.date = date;
        this.time = time;
    }

    public Date getDueDate() {
        return date;
    }

    public void setDueDate(Date date) {
        this.date = date;
    }

    public Time getDueTime() {
        return time;
    }

    public void setDueTime(Time time) {
        this.time = time;
    }

}

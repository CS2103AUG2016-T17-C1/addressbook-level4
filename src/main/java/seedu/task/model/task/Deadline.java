package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

//@@author A0139284X
public class Deadline {

    public static final String MESSAGE_DEADLINE_CONSTRAINTS = "Task's deadline cannot have due endTime only";

    private Date endDate;
    private Time endTime;

    public Deadline(Date date, Time time) throws IllegalValueException {
        if (!date.isProvided() && time.isProvided()) {
            throw new IllegalValueException(MESSAGE_DEADLINE_CONSTRAINTS);
        }
        this.endDate = date;
        this.endTime = time;
    }

    public Date getDueDate() {
        return endDate;
    }

    public void setDueDate(Date date) {
        this.endDate = date;
    }

    public Time getDueTime() {
        return endTime;
    }

    public void setDueTime(Time time) {
        this.endTime = time;
    }

}

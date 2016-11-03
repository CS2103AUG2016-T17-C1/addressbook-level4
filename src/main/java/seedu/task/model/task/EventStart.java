package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

//@@author A0142360U
public class EventStart {

    public static final String MESSAGE_EVENT_START_CONSTRAINTS = "Event must have a start date";

    private Date startDate;
    private Time startTime;


    public EventStart(Date startDate, Time startTime) throws IllegalValueException {
        if (!startDate.isProvided() && startTime.isProvided()) {
            throw new IllegalValueException(MESSAGE_EVENT_START_CONSTRAINTS);
        }
        this.startDate = startDate;
        this.startTime = startTime;
    }


    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Time getStartTime() {
        return startTime;
    }


    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

}

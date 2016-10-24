package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

public class EventStart {

    public static final String MESSAGE_EVENT_START_CONSTRAINTS = "Event must have a start date";

    private StartDate startDate;
    private StartTime startTime;


    public EventStart(StartDate startDate, StartTime startTime) throws IllegalValueException {
        if (startDate.getStartDate().isEmpty() && !(startTime.toString().isEmpty())) {
            throw new IllegalValueException(MESSAGE_EVENT_START_CONSTRAINTS);
        }
        this.startDate = startDate;
        this.startTime = startTime;
        // TODO Auto-generated constructor stub
    }


    public StartDate getStartDate() {
        return startDate;
    }


    public void setStartDate(StartDate startDate) {
        this.startDate = startDate;
    }


    public StartTime getStartTime() {
        return startTime;
    }


    public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }

}

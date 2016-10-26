package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

public class EventStart {

    public static final String MESSAGE_EVENT_START_CONSTRAINTS = "Event must have a start date";

    private Date startDate;
    private Time startTime;


    public EventStart(Date startDate, Time startTime) throws IllegalValueException {
        if (startDate.getDate().isEmpty() && !(startTime.toString().isEmpty())) {
            throw new IllegalValueException(MESSAGE_EVENT_START_CONSTRAINTS);
        }
        this.startDate = startDate;
        this.startTime = startTime;
        // TODO Auto-generated constructor stub
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
    
    //@@author A0139284X
    /** Return formatted concatenated date and time;
     * 
     */
    
    public String toString() {
        StringBuffer start = new StringBuffer();
        
        start.append(getStartTime().toString());
        start.append(getStartDate().toString());
        
        return start.toString();
    }

}

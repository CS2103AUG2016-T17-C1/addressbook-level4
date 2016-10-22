package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

public class DeadLine {
    
    public static final String MESSAGE_DEADLINE_CONSTRAINTS = "Task's deadline cannot have due time only";
    
    private DueDate dueDate;
    private DueTime dueTime;
    

    public DeadLine(DueDate dueDate, DueTime dueTime) throws IllegalValueException {       
        if (dueDate.getDueDate().isEmpty() && !(dueTime.toString().isEmpty())) {
            throw new IllegalValueException(MESSAGE_DEADLINE_CONSTRAINTS);
        }
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        // TODO Auto-generated constructor stub
    }


    public DueDate getDueDate() {
        return dueDate;
    }


    public void setDueDate(DueDate dueDate) {
        this.dueDate = dueDate;
    }


    public DueTime getDueTime() {
        return dueTime;
    }


    public void setDueTime(DueTime dueTime) {
        this.dueTime = dueTime;
    }

}

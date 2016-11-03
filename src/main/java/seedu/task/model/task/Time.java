package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's phone number in the task manager. Guarantees: immutable;
 * is valid as declared in {@link #isValidTime(String)}
 */

public class Time {

    // @@author A0139284X
    public static final String MESSAGE_TIME_CONSTRAINTS = "Task's time should be in 24H format HHMM";
    public static final String MESSAGE_INVALID_TIME = "Time provided is invalid";
    public static final String TIME_VALIDATION_REGEX = "(([0-1]?[0-9]|2[0-3])[0-5][0-9]$){0,1}";
    public static final String INTEGER = "(\\d{4}){0,1}";
    public static final String DELETE_TASK_OBJECT_STRING = "-";

    private final String time;

    // @@author A0142360U
    /**
     * Validates given time.
     *
     * @throws IllegalValueException
     *             if given time string is invalid.
     */
    public Time(String time) throws IllegalValueException {
        if (time == null)
            time = "";
        time = time.trim();
        if (!(time.equals(DELETE_TASK_OBJECT_STRING))) {
            if (!isValidTimeFormat(time)) {
                throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
            }
            if (!isValidTime(time)) {
                throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
            }
        }
        this.time = time;
    }

    /**
     * Returns if a given string is in a valid time format.
     */
    private boolean isValidTimeFormat(String time) {
        return time.matches(INTEGER);
    }

    /**
     * Returns if a given string is a valid Time.
     */
    public static boolean isValidTime(String time) {
        return time.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return getTime();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                        && this.getTime().equals(((Time) other).getTime())); // state
                                                                     // check
    }

    @Override
    public int hashCode() {
        return getTime().hashCode();
    }

    public String getTime() {
        return time;
    }

    //@@author A0139284X
    /**
     * 
     * @return true if Time is null or empty
     */
    
    public boolean isProvided() {
        return !(this.getTime().isEmpty());
    }
    
}

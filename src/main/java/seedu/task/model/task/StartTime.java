package seedu.task.model.task;


import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's phone number in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class StartTime {

    public static final String MESSAGE_START_TIME_CONSTRAINTS =
            "Task's start time should be in 24H format HHMM";
    public static final String START_TIME_VALIDATION_REGEX = "(([0-1]?[0-9]|2[0-3])[0-5][0-9]$){0,1}";

    public final String value;

    /**
     * Validates given email.
     *
     * @throws IllegalValueException if given email address string is invalid.
     */
    public StartTime(String time) throws IllegalValueException {
        if (time == null)
            time = "";
        time = time.trim();
        if (!isValidTime(time)) {
            throw new IllegalValueException(MESSAGE_START_TIME_CONSTRAINTS);
        }
        this.value = time;
    }

    /**
     * Returns if a given string is a valid task email.
     */
    public static boolean isValidTime(String test) {
        return test.matches(START_TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && this.value.equals(((StartTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

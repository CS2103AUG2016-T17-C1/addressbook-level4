package seedu.task.model.task;


import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's phone number in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class DueTime {

    public static final String MESSAGE_DUE_TIME_CONSTRAINTS =
            "Task's due time should be in 24H format HHMM";
    public static final String DUE_TIME_VALIDATION_REGEX = "(([0-1]?[0-9]|2[0-3])[0-5][0-9]$){0,1}";

    public final String value;

    /**
     * Validates given email.
     *
     * @throws IllegalValueException if given email address string is invalid.
     */
    public DueTime(String email) throws IllegalValueException {
        assert email != null;
        email = email.trim();
        if (!isValidEmail(email)) {
            throw new IllegalValueException(MESSAGE_DUE_TIME_CONSTRAINTS);
        }
        this.value = email;
    }

    /**
     * Returns if a given string is a valid task email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(DUE_TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueTime // instanceof handles nulls
                && this.value.equals(((DueTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

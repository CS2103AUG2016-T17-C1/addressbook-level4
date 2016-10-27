package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's phone number in the task manager. Guarantees: immutable;
 * is valid as declared in {@link #isValidDueDate(String)}
 */
public class Date {

    public static final String MESSAGE_DATE_CONSTRAINTS = "Task's date should be in DDMMYYYY format";
    public static final String DATE_VALIDATION_REGEX = "((0[1-9]|([1-2][0-9])|3[01])(0[1-9]|1[0-2])([2-9]\\d{3})$){0,1}";

    private final String date;

    /**
     * Validates given date number.
     *
     * @throws IllegalValueException
     *             if given date string is invalid.
     */
    public Date(String date) throws IllegalValueException {
        if(date == null)
        	date = "";
    	date = date.trim();
        if (!isValidDueDate(date) ) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid task date.
     */
    public static boolean isValidDueDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return getDate();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                        && this.getDate().equals(((Date) other).getDate())); // state
                                                                        // check
    }

    @Override
    public int hashCode() {
        return getDate().hashCode();
    }

    public String getDate() {
        return date;
    }

}

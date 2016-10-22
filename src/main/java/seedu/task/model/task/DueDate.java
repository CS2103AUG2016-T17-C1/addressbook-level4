package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's phone number in the task manager. Guarantees: immutable;
 * is valid as declared in {@link #isValidDueDate(String)}
 */
public class DueDate {

    public static final String MESSAGE_DUE_DATE_CONSTRAINTS = "Task's due date should be in DDMMYYYY format";
    public static final String DUE_DATE_VALIDATION_REGEX = "((0[1-9]|([1-2][0-9])|3[01])(0[1-9]|1[0-2])([2-9]\\d{3})$){0,1}";

    private final String dueDate;

    /**
     * Validates given due date number.
     *
     * @throws IllegalValueException
     *             if given due date string is invalid.
     */
    public DueDate(String dueDate) throws IllegalValueException {
        if(dueDate == null)
        	dueDate = "";
    	dueDate = dueDate.trim();
        if (!isValidDueDate(dueDate) ) {
            throw new IllegalValueException(MESSAGE_DUE_DATE_CONSTRAINTS);
        }
        this.dueDate = dueDate;
    }

    /**
     * Returns true if a given string is a valid task due date.
     */
    public static boolean isValidDueDate(String test) {
        return test.matches(DUE_DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return getDueDate();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueDate // instanceof handles nulls
                        && this.getDueDate().equals(((DueDate) other).getDueDate())); // state
                                                                        // check
    }

    @Override
    public int hashCode() {
        return getDueDate().hashCode();
    }

    public String getDueDate() {
        return dueDate;
    }

}

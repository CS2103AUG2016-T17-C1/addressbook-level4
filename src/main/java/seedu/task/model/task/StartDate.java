package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's phone number in the task manager. Guarantees: immutable;
 * is valid as declared in {@link #isValidDueDate(String)}
 */
public class StartDate {

    public static final String MESSAGE_START_DATE_CONSTRAINTS = "Task's start date should be in DDMMYYYY format";
    public static final String START_DATE_VALIDATION_REGEX = "((0[1-9]|([1-2][0-9])|3[01])(0[1-9]|1[0-2])([2-9]\\d{3})$){0,1}";

    private final String startDate;

    /**
     * Validates given due date number.
     *
     * @throws IllegalValueException
     *             if given due date string is invalid.
     */
    public StartDate(String startDate) throws IllegalValueException {
        if(startDate == null)
            startDate = "";
        startDate = startDate.trim();
        if (!isValidDueDate(startDate) ) {
            throw new IllegalValueException(MESSAGE_START_DATE_CONSTRAINTS);
        }
        this.startDate = startDate;
    }

    /**
     * Returns true if a given string is a valid task due date.
     */
    public static boolean isValidDueDate(String test) {
        return test.matches(START_DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return getStartDate();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDate // instanceof handles nulls
                        && this.getStartDate().equals(((StartDate) other).getStartDate())); // state
                                                                        // check
    }

    @Override
    public int hashCode() {
        return getStartDate().hashCode();
    }

    public String getStartDate() {
        return startDate;
    }

}

package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's phone number in the task manager. Guarantees: immutable;
 * is valid as declared in {@link #isValidDate(String)}
 */
//@@author A0142360U
public class Date {

    // @@author A0139284X
    public static final String MESSAGE_DATE_CONSTRAINTS = "Invalid date, please provide date in DDMMYYYY format";
    public static final String INTEGER = "\\d*";
    public static final String DATE_31DAYS_VALIDATION_REGEX = "((0[1-9]|([1-2][0-9])|3[01])(0[13578]|1[02])([2-9]\\d{3})$){0,1}";
    public static final String DATE_30DAYS_VALIDATION_REGEX = "((0[1-9]|([1-2][0-9])|30)(0[469]|11)([2-9]\\d{3})$){0,1}";
    public static final String DATE_FEB_NONLEAPYEAR_VALIDATION_REGEX = "((0[1-9]|(1[0-9])|2[0-8])(0[1-9]|1[0-2])([2-9]\\d{3})$){0,1}";
    public static final String DATE_FEB_LEAPYEAR_VALIDATION_REGEX = "((0[1-9]|(1[0-9])|2[0-9])(0[1-9]|1[0-2])([2-9]\\d{3})$){0,1}";

    // @@author

    private final String date;

    /**
     * Validates given date number.
     *
     * @throws IllegalValueException
     *             if given date string is invalid.
     */
    public Date(String date) throws IllegalValueException {
        if (date == null)
            date = "";
        date = date.trim();
        if (!isValidDate(date)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.date = date;
    }

    // @@author A0139284X
    /**
     * Returns true if a given string is a valid task date.
     */
    public static boolean isValidDate(String test) {
        if (test.matches(INTEGER)) {
            return test.matches(DATE_31DAYS_VALIDATION_REGEX)
                    || test.matches(DATE_30DAYS_VALIDATION_REGEX)
                    || isValidFebDate(test);
        } else
            return false;
    }

    private static boolean isValidFebDate(String test) {
        if (isLeapYear(test)) {
            return test.matches(DATE_FEB_LEAPYEAR_VALIDATION_REGEX);
        } else
            return test.matches(DATE_FEB_NONLEAPYEAR_VALIDATION_REGEX);
    }

    /**
     * 
     * @param test
     * @return true if is leap year
     */

    private static boolean isLeapYear(String test) {
        int year = Integer.parseInt(test);
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }

    // @@author
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
package seedu.task.model.task;

import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's phone number in the task manager. Guarantees: immutable;
 * is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    // @@author A0139284X
    public static final String MESSAGE_DATE_CONSTRAINTS = "Invalid date format, please provide date in DDMMYYYY format";
    public static final String MESSAGE_INVALID_DATE = "Date provided is invalid";
    public static final String INTEGER = "(\\d{8}){0,1}";
    public static final String DATE_31DAYS_VALIDATION_REGEX = "((0[1-9]|([1-2][0-9])|3[01])(0[13578]|1[02])([2-9]\\d{3})$){0,1}";
    public static final String DATE_30DAYS_VALIDATION_REGEX = "((0[1-9]|([1-2][0-9])|30)(0[469]|11)([2-9]\\d{3})$){0,1}";
    public static final String DATE_FEB_NONLEAPYEAR_VALIDATION_REGEX = "((0[1-9]|(1[0-9])|2[0-8])(0[1-9]|1[0-2])([2-9]\\d{3})$){0,1}";
    public static final String DATE_FEB_LEAPYEAR_VALIDATION_REGEX = "((0[1-9]|(1[0-9])|2[0-9])(0[1-9]|1[0-2])([2-9]\\d{3})$){0,1}";

    private final String date;

    // @@author A0142360U
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
        if (!isValidDateFormat(date)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        if (!isValidDate(date)) {
            throw new IllegalValueException(MESSAGE_INVALID_DATE);
        }
        this.date = date;
    }

    // @@author A0139284X
    /**
     * 
     * @param date
     * @return true if date is in DDMMYYYY format
     */
    
    
    private boolean isValidDateFormat(String date) {
        return date.matches(INTEGER);
    }

    /**
     * Returns true if a given string is a valid task date.
     */
    public static boolean isValidDate(String date) {
        return date.matches(DATE_31DAYS_VALIDATION_REGEX) || date.matches(DATE_30DAYS_VALIDATION_REGEX)
                || isValidFebDate(date);
    }

    private static boolean isValidFebDate(String date) {
        if (isLeapYear(date)) {
            return date.matches(DATE_FEB_LEAPYEAR_VALIDATION_REGEX);
        } else
            return date.matches(DATE_FEB_NONLEAPYEAR_VALIDATION_REGEX);
    }

    /**
     *
     * @param date
     * @return true if is leap year
     */

    private static boolean isLeapYear(String date) {
        int year = Integer.parseInt(date);
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }
    
    //@@author

    @Override
    public String toString() {
        return getDate();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                        && this.getDate().equals(((Date) other).getDate())); // state // check
    }

    @Override
    public int hashCode() {
        return getDate().hashCode();
    }

    public String getDate() {
        return date;
    }
    
    //@@author A0139284X
    /**
     * 
     * @return true if Date is null or empty
     */
    
    public boolean isProvided() {
        return !(this.getDate().isEmpty());
    }

}
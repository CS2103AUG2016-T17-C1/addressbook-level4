package seedu.task.model.task;


import seedu.task.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's address in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidImportance(String)}
 */
public class Importance {

    public static final String MESSAGE_IMPORTANCE_CONSTRAINTS = "Task importance is denoted by number of *";
    public static final String IMPORTANCE_VALIDATION_REGEX = "(\\p{Punct})*";

    public final String value;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Importance(String importance) throws IllegalValueException {
    	 if (importance == null)
         	importance = "";
         this.value = importance;

        if (!isValidImportance(importance)) {
            throw new IllegalValueException(MESSAGE_IMPORTANCE_CONSTRAINTS);
        }


    }

    /**
     * Returns true if a given string is a valid task email.
     */
    public static boolean isValidImportance(String test) {
       if ( test.equals("") || test.matches(IMPORTANCE_VALIDATION_REGEX))
           return true;
       return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Importance // instanceof handles nulls
                && this.value.equals(((Importance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
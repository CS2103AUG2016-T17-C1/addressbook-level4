package seedu.address.model.person;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Importance {

    public static final String MESSAGE_IMPORTANCE_CONSTRAINTS = "Person phone numbers should only contain numbers";
    public static final String IMPORTANCE_VALIDATION_REGEX = "*";

    public Boolean important = false;

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Importance(String importance) throws IllegalValueException {
        assert importance != null;
        importance = importance.trim();
        if (!isValidImportance(importance)) {
            throw new IllegalValueException(MESSAGE_IMPORTANCE_CONSTRAINTS);
        }
        if (importance.equals("*")){
        	important = true;
        	}
        }

    /**
     * Returns true if a given string is a valid person phone number.
     */
    public static boolean isValidImportance(String test) {
        return test.equals(IMPORTANCE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return important.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Importance // instanceof handles nulls
                && this.important.equals(((Importance) other).important)); // state check
    }

    @Override
    public int hashCode() {
        return important.hashCode();
    }

}

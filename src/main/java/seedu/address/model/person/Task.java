package seedu.address.model.person;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.UniqueTagList;

import java.util.Objects;

/**
 * Represents a Person in the importance book.
 * Guarantees: details are present and not null, field values are valiendDated.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private Date endDate;
    private Time endTime;
    private Importance importance;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Date endDate, Time endTime, Importance importance, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, endDate, endTime, importance, tags);
        this.name = name;
        this.endDate = endDate;
        this.endTime = endTime;
        this.importance = importance;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getDate(), source.getTime(), source.getImportance(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }

    public Date getDate() {
        return endDate;
    }

    public Time getTime() {
        return endTime;
    }

    @Override
    public Importance getImportance() {
        return importance;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, endDate, endTime, importance, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

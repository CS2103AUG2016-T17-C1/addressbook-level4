package seedu.task.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.commons.exceptions.DuplicateDataException;
import seedu.task.commons.util.CollectionUtil;

import java.util.*;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTaskList implements Iterable<Task> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching person in the list.
     */
    public static class TaskNotFoundException extends Exception {}

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ArrayList<ObservableList<Task>> savedList = new ArrayList<ObservableList<Task>>();

    /**
     * Constructs empty TaskList.
     */
    public UniqueTaskList() {}

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        savedList.add(internalList);
        internalList.add(toAdd);

        System.out.println("updated savedlist"+ savedList);
        System.out.println("savedList size" + savedList.size());

    }

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        savedList.add(internalList);
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);

        System.out.println("updated savedlist"+ savedList);
        System.out.println("savedList size" + savedList.size());
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    /**
     * Edits the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean edit(ReadOnlyTask toEdit, Task task) throws TaskNotFoundException {
        assert toEdit != null;
        //final boolean taskFoundAndEdited = internalList.set(1,toEdit);
        int indexDel = internalList.indexOf(toEdit);
        savedList.add(internalList);
        if (indexDel == -1 ) {
            throw new TaskNotFoundException();
        }

        internalList.set(indexDel,task);
        return true;
    }


    /**
     * Edits the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean undo(){
        System.out.println("trying to undo");
        System.out.println("savedList size" + savedList.size());
        System.out.println("to be removed"+ savedList.get(savedList.size() - 1));
        savedList.remove(savedList.size() - 1);
        ObservableList<Task> previousList = savedList.get(0);
        System.out.println("to be placed back"+ savedList);

        System.out.println("lol"+ internalList);
        FXCollections.observableArrayList().setAll(0);

        return true;
    }





    public ObservableList<Task> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}

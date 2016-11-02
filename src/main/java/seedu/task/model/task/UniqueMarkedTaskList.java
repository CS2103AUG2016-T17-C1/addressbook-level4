package seedu.task.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.commons.exceptions.DuplicateDataException;
import seedu.task.commons.util.CollectionUtil;
import seedu.task.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.task.model.task.UniqueTaskList.TaskNotFoundException;

import java.util.*;

//@@author A0127720M

/**
 * A list of tasks that enforces uniqueness between its elements and does not
 * allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueMarkedTaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ArrayList<ArrayList<Task>> savedUndoList = new ArrayList<ArrayList<Task>>();
    private final ArrayList<ArrayList<Task>> savedRedoList = new ArrayList<ArrayList<Task>>();
    
    /**
     * Constructs empty TaskList.
     */
    public UniqueMarkedTaskList() {
    }

    /**
     * Returns true if the list contains an equivalent umarked task as the given
     * argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException
     *             if the task to add is a duplicate of an existing task in the
     *             list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }

        saveCurrentTaskList();
        internalList.add(toAdd);

    }

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException
     *             if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        saveCurrentTaskList();
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);

        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    /**
     * Undo the previous edit made to the marked task list.
     * Returns false if restoredList is empty
     */
    public boolean undo() {

        if (savedUndoList.size() >= 1) {
            saveTaskListForRedo();
            internalList.clear();
            ArrayList<Task> restoredList = savedUndoList.get(savedUndoList.size() - 1);
            for (Task t : restoredList) {
                internalList.add(t);
            }
            savedUndoList.remove(savedUndoList.size() - 1);
            return true;
        }

        return false;
    }

    public void saveCurrentTaskList() {
        ArrayList<Task> tempArrayList = new ArrayList<Task>();
        for (Task t : internalList) {
            tempArrayList.add(t);
        }
        savedUndoList.add(tempArrayList);

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
                || (other instanceof UniqueMarkedTaskList // instanceof handles nulls
                        && this.internalList.equals(((UniqueMarkedTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public boolean redo() {
        if (savedRedoList.size() >= 1) {
            saveCurrentTaskList();
            internalList.clear();
            ArrayList<Task> restoredList = savedRedoList.get(savedRedoList.size() - 1);
            for (Task t : restoredList) {
                internalList.add(t);
            }
            savedRedoList.remove(savedRedoList.size() - 1);
            return true;
        }

        return false;
    }
    

    private void saveTaskListForRedo() {
        ArrayList<Task> tempArrayList = new ArrayList<Task>();
        for (Task t : internalList) {
            tempArrayList.add(t);
        }
        savedRedoList.add(tempArrayList);
    }
}

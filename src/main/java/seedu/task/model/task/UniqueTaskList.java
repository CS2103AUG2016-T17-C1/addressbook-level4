package seedu.task.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.commons.exceptions.DuplicateDataException;
import seedu.task.commons.util.CollectionUtil;

import java.util.*;

/**
 * A list of tasks that enforces uniqueness between its elements and does not
 * allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
//@@author A0142360U
public class UniqueTaskList implements Iterable<Task> {

    /**
     * Signals that an operation would have violated the 'no duplicates'
     * property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would
     * fail because there is no such matching person in the list.
     */
    public static class TaskNotFoundException extends Exception {
    }

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ArrayList<ArrayList<Task>> savedUndoList = new ArrayList<ArrayList<Task>>();
    private final ArrayList<ArrayList<Task>> savedRedoList = new ArrayList<ArrayList<Task>>();


    /**
     * Constructs empty TaskList.
     */
    public UniqueTaskList() {
    }

    /**
     * Returns true if the list contains an equivalent task as the given
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
        clearRedoList();
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
        clearRedoList();
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    /**
     * Edits the equivalent task from the list.
     *
     * @throws TaskNotFoundException
     *             if no such task could be found in the list.
     */
    public boolean edit(ReadOnlyTask toEdit, Task task) throws TaskNotFoundException {
        assert toEdit != null;
        int indexDel = internalList.indexOf(toEdit);
        if (indexDel == -1) {
            throw new TaskNotFoundException();
        }
        saveCurrentTaskList();
        clearRedoList();
        internalList.set(indexDel, task);
        return true;
    }

    /**
     * Undo the previous edit made to the task list.
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


    private void saveTaskListForRedo() {
        ArrayList<Task> tempArrayList = new ArrayList<Task>();
        for (Task t : internalList) {
            tempArrayList.add(t);
        }
        savedRedoList.add(tempArrayList);
    }

    /**
     * Reverse the precious undo action made to the task list.
     * Returns false if restoredList is empty
     */
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

    public void clearRedoList(){
        savedRedoList.clear();
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
                || (other instanceof UniqueTaskList // instanceof handles nulls
                        && this.internalList.equals(((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }


}

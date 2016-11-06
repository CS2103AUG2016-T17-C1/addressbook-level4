package seedu.task.model;

import javafx.collections.ObservableList;
import seedu.task.model.tag.Tag;
import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueMarkedTaskList;
import seedu.task.model.task.UniqueTaskList;
import seedu.task.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.task.model.task.UniqueTaskList.TaskNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Wraps all data at the task-manager level Duplicates are not allowed (by
 * .equals comparison)
 */
public class TaskManager implements ReadOnlyTaskManager {

    private final UniqueTaskList tasks;
    private final UniqueMarkedTaskList markedTasks;
    private final UniqueTagList tags;

    {
        tasks = new UniqueTaskList();
        tags = new UniqueTagList();
        markedTasks = new UniqueMarkedTaskList();
    }

    public TaskManager() {
    }

    /**
     * Tasks and Tags are copied into this task manager
     */
    public TaskManager(ReadOnlyTaskManager toBeCopied) {
        this(toBeCopied.getUniqueTaskList(), toBeCopied.getUniqueTagList(), toBeCopied.getUniqueMarkedList());
    }

    /**
     * Tasks and Tags are copied into this task manager
     */
    public TaskManager(UniqueTaskList persons, UniqueTagList tags, UniqueMarkedTaskList incompletedTasks) {
        resetData(persons.getInternalList(), tags.getInternalList(), incompletedTasks.getInternalList());
    }

    public static ReadOnlyTaskManager getEmptyTaskManager() {
        return new TaskManager();
    }

    //// list overwrite operations

    public ObservableList<Task> getTasks() {
        return tasks.getInternalList();
    }

    public ObservableList<Task> getMarkedTasks() {
        return markedTasks.getInternalList();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.getInternalList().setAll(tasks);
        clearRedoArrayList();
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.getInternalList().setAll(tags);
        clearRedoArrayList();
    }

    public void resetData(Collection<? extends ReadOnlyTask> newTasks, Collection<Tag> newTags,
            Collection<? extends ReadOnlyTask> newMarkedTasks) {
        setTasks(newTasks.stream().map(Task::new).collect(Collectors.toList()));
        setMarkedTasks(newMarkedTasks.stream().map(Task::new).collect(Collectors.toList()));
        setTags(newTags);
    }

    private void setMarkedTasks(List<Task> markedTasks) {
        this.markedTasks.getInternalList().setAll(markedTasks);
    }

    // @@author A0142360U
    /*
     * Undo the previous action made by user. If there are no marked tasks to Undo, create a duplicate of the current Marked Tasks in the Marked Tasks Redo list.
     * This is to prevent a premature Redo action of the marked tasks.
     */
    public boolean undo() {
        boolean markedTasksUndo = this.markedTasks.undo();
        boolean tasksUndo = this.tasks.undo();
        if (tasksUndo && !markedTasksUndo) {
            markedTasks.addEmptyListInRedo();
        }

        return tasksUndo;
    }

    public boolean redo() {
        this.markedTasks.redo();
        return this.tasks.redo();
    }

    public void resetData(ReadOnlyTaskManager newData) {
        this.tasks.saveCurrentTaskList();
        this.markedTasks.saveCurrentTaskList();
        resetData(newData.getTaskList(), newData.getTagList(), newData.getMarkedTaskList());
        clearRedoArrayList();

    }

    public void clearRedoArrayList() {
        this.markedTasks.clearMarkedRedoList();
        this.tasks.clearRedoList();
    }

    /*
     * Save Current marked tasks in the Undo ArrayList.
     */
    public void addDuplicateListInUndo() {
        this.markedTasks.addExistingMarkedTaskstInUndoArrayList();
    }
    // @@author

    //// person-level operations

    /**
     * Adds a task to the task manager. Also checks the new task's tags and
     * updates {@link #tags} with any new tags found, and updates the Tag
     * objects in the task to point to those in {@link #tags}.
     *
     * @throws UniqueTaskList.DuplicateTaskException
     *             if an equivalent task already exists.
     */
    public void addTask(Task p) throws UniqueTaskList.DuplicateTaskException {
        //syncTagsWithMasterList(p);
        tasks.add(p);
        clearRedoArrayList();
        addDuplicateListInUndo();
    }

    // @@author A0127720M
    public void mark(ReadOnlyTask taskToMark) throws DuplicateTaskException, TaskNotFoundException {
        tasks.remove(taskToMark);
        markedTasks.add((Task) taskToMark);
        clearRedoArrayList();
    }
    // @@author

    /**
     * Ensures that every tag in this task: - exists in the master list
     * {@link #tags} - points to a Tag object in the master list
     */
    private void syncTagsWithMasterList(Task task) {
        final UniqueTagList taskTags = task.getTags();
        tags.mergeFrom(taskTags);

        // Create map with values = tag object references in the master list
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        for (Tag tag : tags) {
            masterTagObjects.put(tag, tag);
        }

        // Rebuild the list of task tags using references from the master list
        final Set<Tag> commonTagReferences = new HashSet<>();
        for (Tag tag : taskTags) {
            commonTagReferences.add(masterTagObjects.get(tag));
        }
        task.setTags(new UniqueTagList(commonTagReferences));
    }

    public boolean removeTask(ReadOnlyTask key) throws UniqueTaskList.TaskNotFoundException {
        if (tasks.remove(key)) {
            clearRedoArrayList();
            addDuplicateListInUndo();
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
    }

    //@@author A0127720M

    public boolean removeMarkedTask(ReadOnlyTask target) throws TaskNotFoundException {
    	if (markedTasks.remove(target)) {
            clearRedoMarkedArrayList();
            addDuplicateMarkedListInUndo();
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
	}
    //@@author

    private void addDuplicateMarkedListInUndo() {
    	this.tasks.saveCurrentTaskList();

	}

	private void clearRedoMarkedArrayList() {
		this.tasks.clearRedoList();

	}

	public boolean editTask(ReadOnlyTask key, Task newTask) throws UniqueTaskList.TaskNotFoundException {
        if (tasks.edit(key, newTask)) {
            clearRedoArrayList();
            addDuplicateListInUndo();
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
    }

    //// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        clearRedoArrayList();
        addDuplicateListInUndo();
        tags.add(t);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.getInternalList().size() + " tasks, " + tags.getInternalList().size() + " tags";
        // TODO: refine later
    }

    @Override
    public List<ReadOnlyTask> getTaskList() {
        return Collections.unmodifiableList(tasks.getInternalList());
    }

    @Override
    public List<Tag> getTagList() {
        return Collections.unmodifiableList(tags.getInternalList());
    }

    @Override
    public UniqueTaskList getUniqueTaskList() {
        return this.tasks;
    }

    @Override
    public UniqueTagList getUniqueTagList() {
        return this.tags;
    }

    // @@author A0127720M
    @Override
    public List<ReadOnlyTask> getMarkedTaskList() {
        return Collections.unmodifiableList(markedTasks.getInternalList());
    }

    @Override
    public UniqueMarkedTaskList getUniqueMarkedList() {
        return this.markedTasks;
    }
    // @@author

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskManager // instanceof handles nulls
                        && this.tasks.equals(((TaskManager) other).tasks)
                        && this.tags.equals(((TaskManager) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing
        // your own
        return Objects.hash(tasks, tags);
    }

    //@@author A0139284X

    public static ReadOnlyTaskManager getEmptyMarkedTaskManager(ReadOnlyTaskManager taskManager) {
        return new TaskManager(taskManager.getUniqueTaskList(), taskManager.getUniqueTagList(), new UniqueMarkedTaskList());
    }


}
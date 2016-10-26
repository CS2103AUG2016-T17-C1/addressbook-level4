package seedu.task.model;

import javafx.collections.ObservableList;
import seedu.task.model.tag.Tag;
import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueMarkedTaskList;
import seedu.task.model.task.UniqueUnmarkedTaskList;
import seedu.task.model.task.UniqueUnmarkedTaskList.DuplicateTaskException;
import seedu.task.model.task.UniqueUnmarkedTaskList.TaskNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Wraps all data at the task-manager level
 * Duplicates are not allowed (by .equals comparison)
 */
public class TaskManager implements ReadOnlyTaskManager {

    private final UniqueUnmarkedTaskList tasks;
    private final UniqueMarkedTaskList markedTasks;
    private final UniqueTagList tags;

    {
        tasks = new UniqueUnmarkedTaskList();
        tags = new UniqueTagList();
        markedTasks = new UniqueMarkedTaskList();
    }

    public TaskManager() {}

    /**
     * Tasks and Tags are copied into this task manager
     */
    public TaskManager(ReadOnlyTaskManager toBeCopied) {
        this(toBeCopied.getUniqueTaskList(), toBeCopied.getUniqueTagList(), toBeCopied.getUniqueMarkedList());
    }

    /**
     * Tasks and Tags are copied into this task manager
     */
    public TaskManager(UniqueUnmarkedTaskList persons, UniqueTagList tags, UniqueMarkedTaskList incompletedTasks) {
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
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.getInternalList().setAll(tags);
    }

    public void resetData(Collection<? extends ReadOnlyTask> newTasks, Collection<Tag> newTags, Collection<? extends ReadOnlyTask> newMarkedTasks) {
        setTasks(newTasks.stream().map(Task::new).collect(Collectors.toList()));
        setMarkedTasks(newMarkedTasks.stream().map(Task::new).collect(Collectors.toList()));
        setTags(newTags);
    }

    private void setMarkedTasks(List<Task> markedTasks) {
    	this.markedTasks.getInternalList().setAll(markedTasks);
	}

	public boolean undo(){
        System.out.println("undo at TaskManager");
        return this.tasks.undo() && this.markedTasks.undo();
    }

    public void resetData(ReadOnlyTaskManager newData) {
        this.tasks.saveCurrentTaskList();
        resetData(newData.getTaskList(), newData.getTagList(), newData.getMarkedTaskList());
    }
    
    

//// person-level operations

    /**
     * Adds a task to the task manager.
     * Also checks the new task's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the task to point to those in {@link #tags}.
     *
     * @throws UniqueUnmarkedTaskList.DuplicateTaskException if an equivalent task already exists.
     */
    public void addTask(Task p) throws UniqueUnmarkedTaskList.DuplicateTaskException {
        syncTagsWithMasterList(p);
        tasks.add(p);
    }

    
    public void mark(ReadOnlyTask taskToMark) throws DuplicateTaskException, TaskNotFoundException {
		tasks.remove(taskToMark);
		markedTasks.add((Task) taskToMark);
	}
    
    /**
     * Ensures that every tag in this task:
     *  - exists in the master list {@link #tags}
     *  - points to a Tag object in the master list
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

    public boolean removeTask(ReadOnlyTask key) throws UniqueUnmarkedTaskList.TaskNotFoundException {
        if (tasks.remove(key)) {
            return true;
        } else {
            throw new UniqueUnmarkedTaskList.TaskNotFoundException();
        }
    }

    public boolean editTask(ReadOnlyTask key, Task newTask) throws UniqueUnmarkedTaskList.TaskNotFoundException {
        if (tasks.edit(key, newTask)) {
            return true;
        } else {
            throw new UniqueUnmarkedTaskList.TaskNotFoundException();
        }
    }

//// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

//// util methods

    @Override
    public String toString() {
        return tasks.getInternalList().size() + " tasks, " + tags.getInternalList().size() +  " tags";
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
    public UniqueUnmarkedTaskList getUniqueTaskList() {
        return this.tasks;
    }

    @Override
    public UniqueTagList getUniqueTagList() {
        return this.tags;
    }
    
    @Override
    public List<ReadOnlyTask> getMarkedTaskList() {
    	return Collections.unmodifiableList(markedTasks.getInternalList());
    }
    
    @Override 
    public UniqueMarkedTaskList getUniqueMarkedList() {
    	return this.markedTasks;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskManager // instanceof handles nulls
                && this.tasks.equals(((TaskManager) other).tasks)
                && this.tags.equals(((TaskManager) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tasks, tags);
    }

	
}

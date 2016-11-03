package seedu.task.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.tag.Tag;
import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.EventStart;
import seedu.task.model.task.Date;
import seedu.task.model.task.Time;
import seedu.task.model.task.Importance;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.Task;
import seedu.task.model.task.TaskName;
import seedu.task.model.task.UniqueTaskList.TaskNotFoundException;

//@@author A0142360U
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String EMPTY_TASK_OBJECT_STRING = "";
    public static final String EMPTY_TAG_OBJECT_STRING = "[]";
    public static final String DEFAULT_DATE_STRING = "01012000";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": edits the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String DELETE_TASK_OBJECT_STRING = "-";

    public final int targetIndex;
    private final Task toEdit;

    public EditCommand(String string, String taskName, String startDate, String startTime, String dueDate,
            String dueTime, String importance, Set<String> tags) throws IllegalValueException {
        this.targetIndex = Integer.parseInt(string);

        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }

        if ((dueDate == null || dueDate.isEmpty()) && dueTime != null) {
            dueDate = DEFAULT_DATE_STRING;
        } else if (dueDate != null && dueDate.equals(DELETE_TASK_OBJECT_STRING)) {
            dueTime = DELETE_TASK_OBJECT_STRING;
        }

        if ((startDate == null || startDate.isEmpty()) && startTime != null) {
            startDate = DEFAULT_DATE_STRING;
        } else if (startDate != null && startDate.equals(DELETE_TASK_OBJECT_STRING)) {
            startTime = DELETE_TASK_OBJECT_STRING;
        }

        if (taskName == null) {
            this.toEdit = new Task(new EventStart(new Date(startDate), new Time(startTime)),
                    new Deadline(new Date(dueDate), new Time(dueTime)), new Importance(importance),
                    new UniqueTagList(tagSet));
        }

        else {
            this.toEdit = new Task(new TaskName(taskName), new EventStart(new Date(startDate), new Time(startTime)),
                    new Deadline(new Date(dueDate), new Time(dueTime)), new Importance(importance),
                    new UniqueTagList(tagSet));
        }
    }

    @Override
    public CommandResult execute() throws IllegalValueException {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToEdit = lastShownList.get(targetIndex - 1);

        if (taskToEdit.getDeadline().getDueDate().toString().isEmpty()
                && this.toEdit.getDeadline().getDueDate().toString().equals(DEFAULT_DATE_STRING)) {
            return new CommandResult(Deadline.MESSAGE_DEADLINE_CONSTRAINTS);
        }

        if (taskToEdit.getEventStart().getStartDate().toString().isEmpty()
                && this.toEdit.getEventStart().getStartDate().toString().equals(DEFAULT_DATE_STRING)) {
            return new CommandResult(EventStart.MESSAGE_EVENT_START_CONSTRAINTS);
        }
        try {
            if (this.toEdit.getName() == null) {
                this.toEdit.setName(taskToEdit.getName());
            }
            if (this.toEdit.getDeadline().getDueDate().toString().equals(DELETE_TASK_OBJECT_STRING)) {
                this.toEdit.setDueDate(new Date(""));
            } else if (this.toEdit.getDeadline().getDueDate().toString().equals(EMPTY_TASK_OBJECT_STRING)
                    || this.toEdit.getDeadline().getDueDate().toString().equals(DEFAULT_DATE_STRING)) {
                this.toEdit.setDueDate(taskToEdit.getDeadline().getDueDate());
            }
            if (this.toEdit.getDeadline().getDueTime().toString().equals(DELETE_TASK_OBJECT_STRING)) {
                this.toEdit.setDueTime(new Time(""));
            } else if (this.toEdit.getDeadline().getDueTime().toString().equals(EMPTY_TASK_OBJECT_STRING)) {
                this.toEdit.setDueTime(taskToEdit.getDeadline().getDueTime());
            }
            if (this.toEdit.getEventStart().getStartDate().toString().equals(DELETE_TASK_OBJECT_STRING)) {
                this.toEdit.setStartDate(new Date(""));
            } else if (this.toEdit.getEventStart().getStartDate().toString().equals(EMPTY_TASK_OBJECT_STRING)
                    || this.toEdit.getEventStart().getStartDate().toString().equals(DEFAULT_DATE_STRING)) {
                this.toEdit.setStartDate(taskToEdit.getEventStart().getStartDate());
            }
            if (this.toEdit.getEventStart().getStartTime().toString().equals(DELETE_TASK_OBJECT_STRING)) {
                this.toEdit.setStartTime(new Time(""));
            } else if (this.toEdit.getEventStart().getStartTime().toString().equals(EMPTY_TASK_OBJECT_STRING)) {
                this.toEdit.setStartTime(taskToEdit.getEventStart().getStartTime());
            }
            if (this.toEdit.getTags().getInternalList().toString().equals(EMPTY_TAG_OBJECT_STRING)) {
                this.toEdit.setTags(taskToEdit.getTags());
            } else {
                taskToEdit.getTags().mergeFrom(toEdit.getTags());
                this.toEdit.setTags(taskToEdit.getTags());
            }
            if (this.toEdit.getImportance().toString().equals(DELETE_TASK_OBJECT_STRING)) {
                this.toEdit.setImportance(new Importance(""));
            } else if (this.toEdit.getImportance().toString().equals(EMPTY_TASK_OBJECT_STRING)) {
                this.toEdit.setImportance(taskToEdit.getImportance());
            }
            model.editTask(taskToEdit, this.toEdit);

        } catch (TaskNotFoundException pnfe) {
            assert false : "The target task cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, toEdit));
    }

}

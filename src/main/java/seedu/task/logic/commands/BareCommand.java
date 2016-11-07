package seedu.task.logic.commands;

import static seedu.task.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.HashSet;
import java.util.Set;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.events.ui.JumpToListRequestEvent;
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

//@@author A0142360U - reused
public class BareCommand extends Command {

    public static final String COMMAND_WORD = "bare";
    public static final String SHORTCUT = "b";
    public static final String EMPTY_TASK_OBJECT_STRING = "";
    public static final String EMPTY_TAG_OBJECT_STRING = "[]";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": bares the task (identified by the index number) of its date(s) and time(s).\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1\n" + "Hotkey: "
            + SHORTCUT;
    public static final String MESSAGE_BARED_TASK_SUCCESS = "Bared Task: %1$s";
    public static final String DELETE_TASK_OBJECT_STRING = "-";

    public final int targetIndex;
    private final Task toBare;

    public BareCommand(String string, String taskName, String startDate, String startTime, String dueDate,
            String dueTime, String importance, Set<String> tags) throws IllegalValueException {

        // @@author A0152952A

        this.targetIndex = Integer.parseInt(string);

        final Set<Tag> tagSet = new HashSet<>();

        this.toBare = new Task(new EventStart(new Date(startDate), new Time(startTime)),
                new Deadline(new Date(dueDate), new Time(dueTime)), new Importance(importance),
                new UniqueTagList(tagSet));
    }

    @Override
    public CommandResult execute() throws IllegalValueException {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToBare = lastShownList.get(targetIndex - 1);

        try {
            if (this.toBare.getName() == null) {
                this.toBare.setName(taskToBare.getName());
            }

            this.toBare.setDueDate(new Date(""));
            this.toBare.setDueTime(new Time(""));
            this.toBare.setStartDate(new Date(""));
            this.toBare.setStartTime(new Time(""));

            if (this.toBare.getTags().getInternalList().toString().equals(EMPTY_TAG_OBJECT_STRING)) {
                this.toBare.setTags(taskToBare.getTags());
            } else {
                taskToBare.getTags().mergeFrom(toBare.getTags());
                this.toBare.setTags(taskToBare.getTags());
            }
            if (this.toBare.getImportance().toString().equals(DELETE_TASK_OBJECT_STRING)) {
                this.toBare.setImportance(new Importance(""));
            } else if (this.toBare.getImportance().toString().equals(EMPTY_TASK_OBJECT_STRING)) {
                this.toBare.setImportance(taskToBare.getImportance());
            }

            model.editTask(taskToBare, this.toBare);

        } catch (TaskNotFoundException pnfe) {
            assert false : "The target task cannot be missing";
        }

        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex - 1));
        return new CommandResult(String.format(MESSAGE_BARED_TASK_SUCCESS, toBare));
    }

}

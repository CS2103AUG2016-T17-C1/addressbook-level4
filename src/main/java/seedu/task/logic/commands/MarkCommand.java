package seedu.task.logic.commands;

import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.task.model.task.UniqueTaskList.TaskNotFoundException;

//@@author A0127720M

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String SHORTCUT = "m";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the selected task as completed and display the task in the 'completed task panel'.\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1\n"
            + "Hotkey: " + SHORTCUT;
    public static final String MESSAGE_SUCCESS = "Marks a given task: %1$s ";

    private int targetIndex;

    // Construct a new mark command
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() {
        // Check index
        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        // mark a task
        ReadOnlyTask taskToMark = lastShownList.get(targetIndex - 1);
        try {
            model.markTask(taskToMark);
        } catch (TaskNotFoundException pnfe) {
            assert false : "The target task cannot be missing";
        } catch (DuplicateTaskException e) {
            assert false : "Redundant tasks";
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToMark));
    }
}

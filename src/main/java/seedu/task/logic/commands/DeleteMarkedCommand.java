package seedu.task.logic.commands;

import seedu.task.commons.core.Messages;
import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.UniqueTaskList.TaskNotFoundException;

//@@author A0127720M
public class DeleteMarkedCommand extends Command {
	
    public static final String COMMAND_WORD = "deleteM";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Marked Task: %1$s";

    public final int targetIndex;

    public DeleteMarkedCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

	
	@Override
	public CommandResult execute() throws IllegalValueException {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredMarkedTaskList();
        

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToDelete = lastShownList.get(targetIndex - 1);

        try {
            System.out.println("task to delete "+taskToDelete);
            model.deleteMarkedTask(taskToDelete);
        } catch (TaskNotFoundException pnfe) {
            assert false : "The target task cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
	}

}

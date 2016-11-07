package seedu.task.logic.commands;

import java.util.Arrays;
import java.util.Collections;

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

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Marked Task: %1$s\n";

    private static final int FIRST_INDEX = 0;

    public final Integer[] targetIndex;
	
	public DeleteMarkedCommand(Integer[] integers) {
        this.targetIndex = integers;
    }


    @Override
	public CommandResult execute() throws IllegalValueException {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredMarkedTaskList();
        
        Arrays.sort(targetIndex, Collections.reverseOrder());
        
        StringBuilder deleted = new StringBuilder();
        
        if (lastShownList.size() < targetIndex[FIRST_INDEX]) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        
        for (int i : targetIndex) {

            ReadOnlyTask taskToDelete = lastShownList.get(i - 1);

            try {
                System.out.println("task to delete " + taskToDelete);
                model.deleteMarkedTask(taskToDelete);
            } catch (TaskNotFoundException pnfe) {
                assert false : "The target task cannot be missing";
            }
            
            deleted.append(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
            
        }
        
        return new CommandResult(deleted.toString());
    }

}

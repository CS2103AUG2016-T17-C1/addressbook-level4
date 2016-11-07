package seedu.task.logic.commands;

import java.util.Arrays;
import java.util.Collections;

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
    public static final String MESSAGE_SUCCESS = "Marked task: %1$s\n";
    private static final int FIRST_INDEX = 0;

    private Integer[] targetIndex;

    // Construct a new mark command
    public MarkCommand(Integer[] integers) {
        this.targetIndex = integers;
    }

    @Override
    public CommandResult execute() throws DuplicateTaskException {
        // Check index
        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();
        
        Arrays.sort(targetIndex, Collections.reverseOrder());
                
        StringBuilder marked = new StringBuilder();

        if (lastShownList.size() < targetIndex[FIRST_INDEX]) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        
        for (int i : targetIndex) {

            ReadOnlyTask taskToMark = lastShownList.get(i - 1);

            try {
                model.markTask(taskToMark);
            } catch (TaskNotFoundException pnfe) {
                assert false : "The target task cannot be missing";
            }
            
            marked.append(String.format(MESSAGE_SUCCESS, taskToMark));

        }
        
        return new CommandResult(marked.toString());
    }
}

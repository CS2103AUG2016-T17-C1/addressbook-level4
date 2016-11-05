package seedu.task.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.task.commons.core.EventsCenter;
import seedu.task.commons.core.Messages;
import seedu.task.commons.events.ui.JumpToListRequestEvent;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.tag.Tag;
import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.task.*;

/**
 * Adds a task to the task manager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String SHORTCUT = "a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task manager. "
            + "Parameters: TASK_NAME [sd/START_DATE] [st/START_TIME] [d/END_DATE] [e/END_TIME] [*] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " Buy milk d/15102016 e/1500 *";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task is already Never Forgetten";

    private final Task toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String taskName, String startDate, String startTime, String dueDate, String dueTime,
            String importance, Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        
        //@@author A0139824X
        //Data preprocessing
        if (taskName == EditCommand.DELETE_TASK_OBJECT_STRING) {
            taskName = "";
        }
        if (startDate == EditCommand.DELETE_TASK_OBJECT_STRING) {
            startDate = "";
        }
        if (startTime == EditCommand.DELETE_TASK_OBJECT_STRING) {
            startTime = "";
        }
        if (dueDate == EditCommand.DELETE_TASK_OBJECT_STRING) {
            dueDate = "";
        }
        if (dueTime == EditCommand.DELETE_TASK_OBJECT_STRING) {
            dueTime = "";
        }
        if (importance == EditCommand.DELETE_TASK_OBJECT_STRING) {
            importance = "";
        }
        
        //@@author
        
        this.toAdd = new Task(
                new TaskName(taskName),
                new EventStart(new Date(startDate), new Time(startTime)),
                new Deadline(new Date(dueDate), new Time(dueTime)),
                new Importance(importance),
                new UniqueTagList(tagSet)
        );
    }

    @Override
    public CommandResult execute() {
    	
    	// @@author A0152952A
    	if(toAdd.getDeadline().getDueDate().getDate()!="" && toAdd.getEventStart().getStartDate().getDate().compareTo(toAdd.getDeadline().getDueDate().getDate()) > 0)
    		return new CommandResult(Messages.MESSAGE_IMPOSSIBLE_SCHEDULE);
    	
    	else if(toAdd.getDeadline().getDueDate().getDate()!="" && toAdd.getEventStart().getStartDate().getDate().compareTo(toAdd.getDeadline().getDueDate().getDate()) == 0 )
    		if(toAdd.getDeadline().getDueTime().getTime()!="" && toAdd.getEventStart().getStartTime().getTime().compareTo(toAdd.getDeadline().getDueTime().getTime()) >0)
        		return new CommandResult(Messages.MESSAGE_IMPOSSIBLE_SCHEDULE);
    	// @@author
    	
        assert model != null;
        try {
            model.addTask(toAdd);
            
            int targetIndex=model.getFilteredTaskList().size();
            EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex - 1));
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }

    }
    
    

}

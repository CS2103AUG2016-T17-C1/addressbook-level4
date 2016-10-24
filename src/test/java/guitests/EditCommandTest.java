package guitests;

import guitests.guihandles.TaskCardHandle;
import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;
import seedu.task.commons.core.Messages;
import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.EditCommand;

import static org.junit.Assert.assertTrue;

public class EditCommandTest extends TaskManagerGuiTest{
	
	@Test
	public void edit() {
		//edit one task
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToEdit = td.hoon;
        currentList = TestUtil.editTasksToList(currentList, 1 , taskToEdit);
        assertEditSuccess(taskToEdit, 2, currentList);
        
        //edit another task
        taskToEdit = td.ida;
        currentList = TestUtil.editTasksToList(currentList, 2, taskToEdit);
        assertEditSuccess(taskToEdit, 3 ,currentList);
        
        //edit empty list
        commandBox.runcommand("clear");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        
        //invalid index
        commandBox.runCommand("edit x");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        
        //invalid command
        commandBox.runCommand("edits 1");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        
        //incomplete parameters
        commandBox.runCommand("edit 1");
        assertResultMessage(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
	}
	
    
    
	private void assertEditSuccess(TestTask taskToEdit, int index, TestTask... currentList) {
        //commandBox.runCommand(taskToEdit. (index));

        //confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(taskToEdit.getName().fullName);
        assertMatching(taskToEdit, editedCard);

        //confirm the list now contains all previous tasks plus the new edited task
     //   TestTask[] expectedList = TestUtil.editTasksToList(currentList,index-1, taskToEdit);
     //   assertTrue(taskListPanel.isListMatching(expectedList)); 

    }

}

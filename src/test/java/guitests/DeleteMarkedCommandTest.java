package guitests;

import org.junit.Test;
import seedu.address.testutil.TestTask;
import seedu.task.logic.commands.DeleteMarkedCommand;

import static seedu.task.logic.commands.DeleteMarkedCommand.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

//@@author A0127720M
public class DeleteMarkedCommandTest extends TaskManagerGuiTest {
	@Test
	public void deleteMarked(){
        //delete the first marked task in the list
        TestTask[] currentList = td.getTypicalTasks();
        commandBox.runCommand("mark 1");
        int targetIndex = 1;
        assertDeleteSuccess(targetIndex, currentList);
        commandBox.runCommand("undo");
        
        //delete the second marked task in the list
        commandBox.runCommand("mark 1");
        targetIndex = 2;
        assertDeleteSuccess(targetIndex, currentList);
        commandBox.runCommand("undo");
        
        //delete the third marked task in the list
        commandBox.runCommand("mark 1");
        targetIndex = 3;
        assertDeleteSuccess(targetIndex, currentList);
        
        //invalid index
        commandBox.runCommand("deleteM 10");
        assertResultMessage(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        
        //invalid format
        commandBox.runCommand("deleteM");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMarkedCommand.MESSAGE_USAGE));
	}
	
    private void assertDeleteSuccess(int targetIndexOneIndexed, final TestTask[] currentList) {
        TestTask taskToDelete = currentList[targetIndexOneIndexed-1];
        commandBox.runCommand("deleteM " + targetIndexOneIndexed);

        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }
}
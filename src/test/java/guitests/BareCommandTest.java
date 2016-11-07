package guitests;

import org.junit.Test;
import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;

import static org.junit.Assert.assertTrue;
import static seedu.task.logic.commands.BareCommand.MESSAGE_BARED_TASK_SUCCESS;

public class BareCommandTest extends TaskManagerGuiTest {
	// @@author A0152952A

    @Test
    public void bare() {

        //bare the first in the list
        TestTask[] currentList = td.getTypicalBaredTasks();
        int targetIndex = 1;
        assertBareSuccess(targetIndex, currentList);

        //bare the last in the list
        targetIndex = currentList.length;
        assertBareSuccess(targetIndex, currentList);

        //bare the task in the middle of the list
        targetIndex = currentList.length/2;
        assertBareSuccess(targetIndex, currentList);

        //invalid index
        commandBox.runCommand("bare " + currentList.length + 1);
        assertResultMessage("The task index provided is invalid");

    }

    /**
     * Runs the bare command to bare the task at specified index and confirms the result is correct.
     * @param targetIndexOneIndexed e.g. to bare the first task in the list, 1 should be given as the target index.
     * @param currentList A copy of the current list of tasks (after baring).
     */
    private void assertBareSuccess(int targetIndexOneIndexed, final TestTask[] currentList) {
        TestTask taskToBare = currentList[targetIndexOneIndexed-1]; //-1 because array uses zero indexing

        commandBox.runCommand("bare " + targetIndexOneIndexed);

        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_BARED_TASK_SUCCESS, taskToBare));
    }

}

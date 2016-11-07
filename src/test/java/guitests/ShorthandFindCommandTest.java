package guitests;

import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.task.commons.core.Messages;

import static org.junit.Assert.assertTrue;

public class ShorthandFindCommandTest extends TaskManagerGuiTest {
	// @@author A0152952A
    @Test
    public void find_nonEmptyList() {
        assertFindResult("f TV series"); //no results
        assertFindResult("f Call", td.alice, td.mom); //multiple results
        //assertFindResult("find Dinner"); //multiple results

        //find after deleting one result
        commandBox.runCommand("delete 1");
        assertFindResult("f Call",td.mom);
        
        //find after baring
        commandBox.runCommand("bare 1");
        assertFindResult("f Call",td.baremom);
    }

    @Test
    public void find_emptyList(){
        
        commandBox.runCommand("clear");
        assertFindResult("f Supper"); //no results
    }

    @Test
    public void find_invalidCommand_fail() {
        commandBox.runCommand("fshopping");
        // @@author 
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertFindResult(String command, TestTask... expectedHits ) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}

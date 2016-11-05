package guitests;

import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.task.commons.core.Messages;

import static org.junit.Assert.assertTrue;

public class FindCommandTest extends TaskManagerGuiTest {

    @Test
    public void find_nonEmptyList() {
        assertFindResult("find Breakfast"); //no results
        assertFindResult("find Buy", td.bread, td.gifts); //multiple results
        //assertFindResult("find Dinner");//, td.benson, td.daniel); //multiple results

        //find after deleting one result
        commandBox.runCommand("delete 1");
        assertFindResult("find Buy",td.gifts);
    }

    @Test
    public void find_emptyList(){
        
        commandBox.runCommand("clear");
        assertFindResult("find Lunch"); //no results
    }

    @Test
    public void find_invalidCommand_fail() {
        commandBox.runCommand("findgroceries");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertFindResult(String command, TestTask... expectedHits ) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}

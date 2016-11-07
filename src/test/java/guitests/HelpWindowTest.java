package guitests;

import guitests.guihandles.HelpWindowHandle;
import org.junit.Test;
import guitests.GuiRobot;
import guitests.guihandles.GuiHandle;

import static org.junit.Assert.assertTrue;

import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.BareCommand;
import seedu.task.logic.commands.ChangeDirectoryCommand;
import seedu.task.logic.commands.ClearCommand;
import seedu.task.logic.commands.ClearMarkedCommand;
import seedu.task.logic.commands.DeleteCommand;
import seedu.task.logic.commands.DeleteMarkedCommand;
import seedu.task.logic.commands.EditCommand;
import seedu.task.logic.commands.ExitCommand;
import seedu.task.logic.commands.FindCommand;
import seedu.task.logic.commands.MarkCommand;
import seedu.task.logic.commands.RedoCommand;
import seedu.task.logic.commands.SelectCommand;
import seedu.task.logic.commands.UndoCommand;

//@@author A0127720M

public class HelpWindowTest extends TaskManagerGuiTest {

      @Test
    public void checkHelpCommand() {

        taskListPanel.clickOnListView();

        assertHelpWindowOpen(mainMenu.openHelpWindowUsingAccelerator());

        assertHelpWindowOpen(mainMenu.openHelpWindowUsingMenu());

        commandBox.runHelpCommand();
        
        //check the usage for 'add' command
        commandBox.runCommand("help add");
        assertResultMessage(AddCommand.MESSAGE_USAGE);
        
        //check the usage for 'delete' command
        commandBox.runCommand("help delete");
        assertResultMessage(DeleteCommand.MESSAGE_USAGE);
        
        //check the usage for 'deleteM' command
        commandBox.runCommand("help deleteM");
        assertResultMessage(DeleteMarkedCommand.MESSAGE_USAGE);
        
        //check the usage for 'bare' command
        commandBox.runCommand("help bare");
        assertResultMessage(BareCommand.MESSAGE_USAGE);
        
        //check the usage for 'edit' command
        commandBox.runCommand("help edit");
        assertResultMessage(EditCommand.MESSAGE_USAGE);
        
        //check the usage for 'find' command
        commandBox.runCommand("help find");
        assertResultMessage(FindCommand.MESSAGE_USAGE);
        
        //check the usage for 'clear' command
        commandBox.runCommand("help clear");
        assertResultMessage(ClearCommand.MESSAGE_USAGE);
        
        //check the usage for 'cd' command
        commandBox.runCommand("help cd");
        assertResultMessage(ChangeDirectoryCommand.MESSAGE_USAGE);
        
        //check the usage for 'clearM' command
        commandBox.runCommand("help clearM");
        assertResultMessage(ClearMarkedCommand.MESSAGE_USAGE);
        
        //check the usage for 'exit' command
        commandBox.runCommand("help exit");
        assertResultMessage(ExitCommand.MESSAGE_USAGE);
        
        //check the usage for 'redo' command
        commandBox.runCommand("help redo");
        assertResultMessage(RedoCommand.MESSAGE_USAGE);
        
        //check the usage for 'mark' command
        commandBox.runCommand("help mark");
        assertResultMessage(MarkCommand.MESSAGE_USAGE);        

        //check the usage for 'select' command
        commandBox.runCommand("help select");
        assertResultMessage(SelectCommand.MESSAGE_USAGE);
        
        //check the usage for 'undo' command
        commandBox.runCommand("help undo");
        assertResultMessage(UndoCommand.MESSAGE_USAGE);
    }
      

    private void assertHelpWindowOpen(HelpWindowHandle helpWindowHandle) {
        assertTrue(helpWindowHandle.isWindowOpen());
        helpWindowHandle.closeWindow();
    }
 
}

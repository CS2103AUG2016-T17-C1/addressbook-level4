package guitests;

import guitests.guihandles.TaskCardHandle;
import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;
import seedu.task.commons.core.Messages;
import seedu.task.logic.commands.AddCommand;

import static org.junit.Assert.assertTrue;

public class RedoCommandTest extends TaskManagerGuiTest {

    @Test
    public void Redo() {
        //add one task
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToAdd = td.supervisor;
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);


        //add another task
        taskToAdd = td.reserve;
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);

        //undo "add another task"
        assertUndoSuccess();

        //undo "add one task"
        assertUndoSuccess();

        //no more 'undo' action available
        assertUndoFailure();

        //reverse first undo command
        assertRedoSuccess();

        //reverse second undo command
        assertRedoSuccess();

        //no more undo command to reverse
        assertRedoFailure();

        //edit first task on the list
        commandBox.runCommand("edit 1 sd/10102016");
        //undo that command
        assertUndoSuccess();

        //use any command other than "undo"
        commandBox.runCommand("clear");

        //redo command should no longer be available
        assertRedoFailure();



    }

    private void assertAddSuccess(TestTask taskToAdd, TestTask... currentList) {
        commandBox.runCommand(taskToAdd.getAddCommand());

        //confirm the new card contains the right data
        TaskCardHandle addedCard = taskListPanel.navigateToTask(taskToAdd.getName().fullName);
        assertMatching(taskToAdd, addedCard);

        //confirm the list now contains all previous tasks plus the new task
        TestTask[] expectedList = TestUtil.addTasksToList(currentList, taskToAdd);
        assertTrue(taskListPanel.isListMatching(expectedList));
    }


    private void assertUndoSuccess() {
        commandBox.runCommand("undo");
        assertResultMessage("Your changes are undone.");
    }

    private void assertUndoFailure() {
        commandBox.runCommand("undo");
        assertResultMessage("No more changes can be undone.");
    }

    private void assertRedoSuccess() {
        commandBox.runCommand("redo");
        assertResultMessage("Your Undo command has been reversed.");
    }

    private void assertRedoFailure() {
        commandBox.runCommand("redo");
        assertResultMessage("No more Undo command can be reversed.");
    }



}

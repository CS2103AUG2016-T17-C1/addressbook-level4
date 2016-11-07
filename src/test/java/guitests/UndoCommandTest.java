package guitests;

import guitests.guihandles.TaskCardHandle;
import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;
import seedu.task.commons.core.Messages;
import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.UndoCommand;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
//@@author A0142360U
public class UndoCommandTest extends TaskManagerGuiTest {

    @Test
    public void undo() {
        // add one task
        TestTask[] currentList = td.getTypicalTasks();
        TestTask[] emptyList = new TestTask[0];
        TestTask taskToAdd = td.supervisor;
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);

        // add another task
        taskToAdd = td.reserve;
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);

        // add duplicate task
        commandBox.runCommand(td.supervisor.getAddCommand());
        assertResultMessage(AddCommand.MESSAGE_DUPLICATE_TASK);

        assertTrue(taskListPanel.isListMatching(currentList));

        // add to empty list
        commandBox.runCommand("clear");

        assertAddSuccess(td.alice);

        // undo add alice
        assertUndoSuccess();

        // task list should now be empty
        assertTrue(taskListPanel.isListMatching(emptyList));

        // undo clear
        assertUndoSuccess();

        // all previous tasks should be restored except for td.alice
        assertTrue(taskListPanel.isListMatching(currentList));

        // undo add td.reserve
        assertUndoSuccess();

        // cuurentList should have typicaltasks and td.supervisor
        currentList = td.getTypicalTasks();
        currentList = TestUtil.addTasksToList(currentList, td.supervisor);

        assertTrue(taskListPanel.isListMatching(currentList));

        // undo add td.supervisor
        assertUndoSuccess();

        currentList = td.getTypicalTasks();
        assertTrue(taskListPanel.isListMatching(currentList));

        // no more commands to undo
        assertUndoFailure();

        //current TaskList should remain the same
        assertTrue(taskListPanel.isListMatching(currentList));
    }

    private void assertAddSuccess(TestTask taskToAdd, TestTask... currentList) {
        commandBox.runCommand(taskToAdd.getAddCommand());

        // confirm the new card contains the right data
        TaskCardHandle addedCard = taskListPanel.navigateToTask(taskToAdd.getName().fullName);
        assertMatching(taskToAdd, addedCard);

        // confirm the list now contains all previous tasks plus the new task
        TestTask[] expectedList = TestUtil.addTasksToList(currentList, taskToAdd);
        assertTrue(taskListPanel.isListMatching(expectedList));
    }

    private void assertUndoSuccess() {
        commandBox.runCommand("undo");
        assertResultMessage(String.format(UndoCommand.MESSAGE_SUCCESS, 1));
    }

    private void assertUndoFailure() {
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_FAIL);
    }

}

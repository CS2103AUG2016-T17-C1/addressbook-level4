package guitests;

import guitests.guihandles.TaskCardHandle;
import org.junit.Test;

import seedu.address.testutil.TestTask;
import seedu.address.testutil.TestUtil;
import seedu.task.commons.core.Messages;
import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.UndoCommand;

import static org.junit.Assert.assertTrue;

public class UndoCommandTest extends TaskManagerGuiTest {

    @Test
    public void undo() {
        // add one task
        TestTask[] currentList = td.getTypicalTasks();
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

        // undo clear
        assertUndoSuccess();

        // undo add td.reserve
        assertUndoSuccess();

        // undo add td.supervisor
        assertUndoSuccess();

        // no more commands to undo
        assertUndoFailure();

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

# A0152952A  reused
###### \java\guitests\ShorthandBareCommandTest.java
``` java

    @Test
    public void bare() {

        //bare the second last task in the list
        TestTask[] currentList = td.getTypicalBaredTasks();
        int targetIndex = currentList.length - 1;
        assertBareSuccess(targetIndex, currentList);

        //bare the task at index 4
        targetIndex = 4;
        assertBareSuccess(targetIndex, currentList);

        //bare the task at index 5
        targetIndex = 5;
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
```

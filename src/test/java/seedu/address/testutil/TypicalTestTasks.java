package seedu.address.testutil;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.TaskManager;
import seedu.task.model.task.*;

/**
 *
 */
public class TypicalTestTasks {

    public static TestTask alice, benson, carl, daniel, elle, fiona, george, hoon;
    public static TestTask ida;

    public TypicalTestTasks() {
        try {
            alice = new TaskBuilder().withName("Call Alice").withEventStart("11112222", "0000")
                    .withDeadLine("11112222", "1107").withImportance("*").withTags("friends").build();
            benson = new TaskBuilder().withName("Buy Bread").withEventStart("11112000", "0900")
                    .withDeadLine("26122000", "1000").withImportance("**").withTags("wholeGrain", "toast").build();
            carl = new TaskBuilder().withName("Call Mom").withEventStart("11112200", "0900")
                    .withDeadLine("11102016", "2300").withImportance("**").build();
            
            daniel = new TaskBuilder().withName("Buy gifts").withEventStart("11112200", "2000")
                    .withDeadLine("11102016", "0000").withImportance("*").build();
            elle = new TaskBuilder().withName("Join Barbecue").withEventStart("11112200", "1000")
                    .withDeadLine("11102016", "0900").withImportance("**").build();
//            daniel = new TaskBuilder().withName("Join Barbecue").withEventStart("11112200", "2000")
//                    .withDeadLine("11102016", "0000").withImportance("*").build();
//            elle = new TaskBuilder().withName("Buy gifts").withEventStart("11112200", "1000")
//                    .withDeadLine("11102016", "0900").withImportance("**").build();
            fiona = new TaskBuilder().withName("Reply to emails").withEventStart("11112200", "1000")
                    .withDeadLine("11102016", "1000").withImportance("***").build();
            george = new TaskBuilder().withName("Send best wishes to George").withEventStart("12102016", "0000")
                    .withDeadLine("11102016", "2359").withImportance("**").build();

            //Manually added
            hoon = new TaskBuilder().withName("Meet supervisor").withEventStart("11102016","1000").withDeadLine("11102016", "1100").withImportance("*").build();
            ida = new TaskBuilder().withName("Reserve seats").withEventStart("11102016","1000").withDeadLine("11102016", "1400").withImportance("**").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager ab) {

        try {
            ab.addTask(new Task(alice));
            ab.addTask(new Task(benson));
            ab.addTask(new Task(carl));
            ab.addTask(new Task(daniel));
            ab.addTask(new Task(elle));
            ab.addTask(new Task(fiona));
            ab.addTask(new Task(george));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            assert false : "not possible";
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public TaskManager getTypicalAddressBook(){
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}

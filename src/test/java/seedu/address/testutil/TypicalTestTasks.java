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
            alice =  new TaskBuilder().withName("Alice Pauline").withImportance("*")
                    .withEventStart("11112111","1107")
                    .withDeadLine("11112111", "1107").withTags("friends").build();
            benson = new TaskBuilder().withName("Benson Meier").withImportance("**")
                    .withEventStart("11112000","0900")
                    .withDeadLine("26122000", "1000")
                    .withTags("owesMoney", "friends").build();
            carl = new TaskBuilder().withName("Carl Kurzs").withEventStart("11112200","0900").withDeadLine("11102016", "2300").withImportance("**").build();
            daniel = new TaskBuilder().withName("Daniel Meier").withEventStart("11112200","1000").withDeadLine("11102016", "0019").withImportance("*").build();
            elle = new TaskBuilder().withName("Elle Meyer").withEventStart("11112200","1000").withDeadLine("11102016", "0900").withImportance("**").build();
            fiona = new TaskBuilder().withName("Fiona Kunz").withEventStart("11112200","1000").withDeadLine("11102016", "1000").withImportance("***").build();
            george = new TaskBuilder().withName("George Best").withEventStart("11102016","2359").withDeadLine("11102016", "2359").withImportance("**").build();

            //Manually added
            hoon = new TaskBuilder().withName("Hoon Meier").withEventStart("11112100","0100").withDeadLine("11102016", "1100").withImportance("*").build();
            ida = new TaskBuilder().withName("Ida Mueller").withEventStart("11112015","1000").withDeadLine("11102016", "1200").withImportance("**").build();
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

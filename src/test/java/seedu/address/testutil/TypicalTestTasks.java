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
                    .withDueTime("2359").withDueDate("11112111")
                    .withTags("friends").build();
            benson = new TaskBuilder().withName("Benson Meier").withImportance("**")
                    .withDueTime("0000").withDueDate("26122000")
                    .withTags("owesMoney", "friends").build();
            carl = new TaskBuilder().withName("Carl Kurz").withDueDate("11102016").withDueTime("2300").withImportance("**").build();
            daniel = new TaskBuilder().withName("Daniel Meier").withDueDate("11102016").withDueTime("0019").withImportance("*").build();
            elle = new TaskBuilder().withName("Elle Meyer").withDueDate("11102016").withDueTime("0900").withImportance("**").build();
            fiona = new TaskBuilder().withName("Fiona Kunz").withDueDate("11102016").withDueTime("1000").withImportance("***").build();
            george = new TaskBuilder().withName("George Best").withDueDate("11102016").withDueTime("2359").withImportance("**").build();

            //Manually added
            hoon = new TaskBuilder().withName("Hoon Meier").withDueDate("11102016").withDueTime("1100").withImportance("*").build();
            ida = new TaskBuilder().withName("Ida Mueller").withDueDate("11102016").withDueTime("1200").withImportance("**").build();
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

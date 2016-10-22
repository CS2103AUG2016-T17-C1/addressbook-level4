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
                    .withDeadLine("11112111", "1107").withTags("friends").build();
            benson = new TaskBuilder().withName("Benson Meier").withImportance("**")
                    .withDeadLine("26122000", "0000")
                    .withTags("owesMoney", "friends").build();
            carl = new TaskBuilder().withName("Carl Kurz").withDeadLine("11102016", "2300").withImportance("**").build();
            daniel = new TaskBuilder().withName("Daniel Meier").withDeadLine("11102016", "0019").withImportance("*").build();
            elle = new TaskBuilder().withName("Elle Meyer").withDeadLine("11102016", "0900").withImportance("**").build();
            fiona = new TaskBuilder().withName("Fiona Kunz").withDeadLine("11102016", "1000").withImportance("***").build();
            george = new TaskBuilder().withName("George Best").withDeadLine("11102016", "2359").withImportance("**").build();

            //Manually added
            hoon = new TaskBuilder().withName("Hoon Meier").withDeadLine("11102016", "1100").withImportance("*").build();
            ida = new TaskBuilder().withName("Ida Mueller").withDeadLine("11102016", "1200").withImportance("**").build();
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

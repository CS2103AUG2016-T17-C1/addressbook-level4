package seedu.address.testutil;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.TaskManager;
import seedu.task.model.task.*;

/**
 *
 */
public class TypicalTestTasks {
	// @@author A0152952A

    public static TestTask alice, bread, mom, gifts, barbecue, reply, george, supervisor, barereply;
    public static TestTask reserve, groceries, dinner, games, barealice, baregeorge, baremom, baregifts, barebarbecue;

    public TypicalTestTasks() {
        try {
            alice = new TaskBuilder().withName("Call Alice").withEventStart("11112222", "0000")
                    .withDeadLine("11112222", "1107").withImportance("*").withTags("friends").build();
            bread = new TaskBuilder().withName("Buy Bread").withEventStart("11112000", "0900")
                    .withDeadLine("26122000", "1000").withImportance("**").withTags("wholeGrain", "toast").build();
            mom = new TaskBuilder().withName("Call Mom").withEventStart("11112200", "0900")
                    .withDeadLine("11102016", "2300").withImportance("**").build();
            
            gifts = new TaskBuilder().withName("Buy gifts").withEventStart("11112200", "2000")
                    .withDeadLine("11102016", "0000").withImportance("*").build();
            barbecue = new TaskBuilder().withName("Join Barbecue").withEventStart("11112200", "1000")
                    .withDeadLine("11102016", "0900").withImportance("**").build();
            reply = new TaskBuilder().withName("Reply to emails").withEventStart("11112200", "1000")
                    .withDeadLine("11102016", "1000").withImportance("***").build();
            george = new TaskBuilder().withName("Send best wishes to George").withEventStart("12102016", "0000")
                    .withDeadLine("11102016", "2359").withImportance("**").build();
            

            //Manually added
            supervisor = new TaskBuilder().withName("Meet supervisor").withEventStart("11102016","1000").withDeadLine("11102016", "1100").withImportance("*").build();
            reserve = new TaskBuilder().withName("Reserve seats").withEventStart("11102016","1000").withDeadLine("11102016", "1400").withImportance("**").build();
            groceries = new TaskBuilder().withName("Buy groceries").withEventStart("16112016","1900").withDeadLine("16112016", "2200").withImportance("*").build();
            
            dinner = new TaskBuilder().withName("Join family dinner").withEventStart("20112016","2000").withDeadLine("20112016", "2100").withImportance("**").build();
            games = new TaskBuilder().withName("Play video games").withEventStart("08112016","2000").withDeadLine("08112016", "2300").withImportance("").build();
            barealice = new TaskBuilder().withName("Call Alice").withEventStart("","").withDeadLine("", "").withImportance("*").withTags("friends").build();
            
            baregeorge = new TaskBuilder().withName("Send best wishes to George").withEventStart("", "").withDeadLine("", "").withImportance("**").build();
            baremom = new TaskBuilder().withName("Call Mom").withEventStart("", "").withDeadLine("", "").withImportance("**").build();
            baregifts = new TaskBuilder().withName("Buy gifts").withEventStart("", "").withDeadLine("", "").withImportance("*").build();
            
            barebarbecue = new TaskBuilder().withName("Join Barbecue").withEventStart("", "").withDeadLine("", "").withImportance("**").build();
            barereply = new TaskBuilder().withName("Reply to emails").withEventStart("", "").withDeadLine("", "").withImportance("***").build();
            // @@author
            
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager ab) {

        try {
            ab.addTask(new Task(alice));
            ab.addTask(new Task(bread));
            ab.addTask(new Task(mom));
            ab.addTask(new Task(gifts));
            ab.addTask(new Task(barbecue));
            ab.addTask(new Task(reply));
            ab.addTask(new Task(george));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            assert false : "not possible";
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[]{alice, bread, mom, gifts, barbecue, reply, george};
    }
    
    // @@author A0152952A
    public TestTask[] getTypicalBaredTasks() {
        return new TestTask[]{barealice, bread, baremom, baregifts, barebarbecue, barereply, baregeorge};
    }
    // @@author
    
    public TaskManager getTypicalAddressBook(){
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}

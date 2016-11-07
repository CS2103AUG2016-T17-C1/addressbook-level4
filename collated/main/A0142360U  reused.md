# A0142360U  reused
###### \java\seedu\task\logic\commands\BareCommand.java
``` java
public class BareCommand extends Command {

    public static final String COMMAND_WORD = "bare";
    public static final String SHORTCUT = "b";
    public static final String EMPTY_TASK_OBJECT_STRING = "";
    public static final String EMPTY_TAG_OBJECT_STRING = "[]";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": bares the task (identified by the index number) of its date(s) and time(s).\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1\n" + "Hotkey: "
            + SHORTCUT;
    public static final String MESSAGE_BARED_TASK_SUCCESS = "Bared Task: %1$s";
    public static final String DELETE_TASK_OBJECT_STRING = "-";

    public final int targetIndex;
    private final Task toBare;

    public BareCommand(String string, String taskName, String startDate, String startTime, String dueDate,
            String dueTime, String importance, Set<String> tags) throws IllegalValueException {

```

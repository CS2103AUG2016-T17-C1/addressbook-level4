# A0152952A  reused
###### \java\seedu\task\logic\parser\Parser.java
``` java
    private Command prepareBare(String args) {
        final Matcher matcher = EDIT_TASK_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BareCommand.MESSAGE_USAGE));
        }
        try {
            return new BareCommand(matcher.group("targetIndex"), matcher.group("taskName"), matcher.group("startDate"),
                    matcher.group("startTime"), matcher.group("dueDate"), matcher.group("dueTime"),
                    matcher.group("importance"), getTagsFromArgs(matcher.group("tagArguments")));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }
```

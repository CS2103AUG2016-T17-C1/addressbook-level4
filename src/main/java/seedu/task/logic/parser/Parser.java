package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.task.commons.core.Config;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.commons.util.StringUtil;
import seedu.task.logic.commands.*;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    private static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\w+(?:\\s+\\w+)*)"); // one
                                                                                                           // or
                                                                                                           // more
                                                                                                           // keywords
                                                                                                           // separated
                                                                                                           // by
                                                                                                           // whitespace

    private static final Pattern TASK_DATA_ARGS_FORMAT = // '/' forward slashes
                                                         // are reserved for
                                                         // delimiter prefixes
            Pattern.compile("(?<taskName>[^/]+)" + "( sd/(?<startDate>[^/]*)){0,1}" + "( st/(?<startTime>[^/]*)){0,1}"
                    + "( d/(?<dueDate>[^/]*)){0,1}" + "( e/(?<dueTime>[^/]*)){0,1}" + "( i/(?<importance>[^/]*)){0,1}"
                    + "(?<tagArguments>(?: t/[^/]+)*)"); // variable number of
                                                         // tags

    private static final Pattern EDIT_TASK_DATA_ARGS_FORMAT = // '/' forward
                                                              // slashes are
                                                              // reserved for
                                                              // delimiter
                                                              // prefixes
            Pattern.compile("(?<targetIndex>\\d+)" + "(?<taskName>[^/]+){0,1}" + "( sd/(?<startDate>[^/]*)){0,1}"
                    + "( st/(?<startTime>[^/]*)){0,1}" + "( d/(?<dueDate>[^/]*)){0,1}" + "( e/(?<dueTime>[^/]*)){0,1}"
                    + "( i/(?<importance>[^/]*)){0,1}" + "(?<tagArguments>(?: t/[^/]+)*)"); // variable
                                                                                            // number
                                                                                            // of
                                                                                            // tags
    private static final String EMPTY_STRING = "";

    private static final String TARGET_INDEX_SPLIT = " ";

    private static final int FIRST_INDEX = 0;

    private Config config;

    public Parser() {
    }

    public Parser(Config config) {
        this.config = config;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput
     *            full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.SHORTCUT:
        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        // @@author A0152952A
        case BareCommand.SHORTCUT:
        case BareCommand.COMMAND_WORD:
            return prepareBare(arguments);

        case EditCommand.SHORTCUT:
            // @@author
        case EditCommand.COMMAND_WORD:
            return prepareEdit(arguments);

        case SelectCommand.SHORTCUT:
        case SelectCommand.COMMAND_WORD:
            return prepareSelect(arguments);

        case DeleteCommand.SHORTCUT:
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case DeleteMarkedCommand.COMMAND_WORD:
            return prepareDeleteMarked(arguments);

        case ClearMarkedCommand.COMMAND_WORD:
            return new ClearMarkedCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case MarkCommand.SHORTCUT:
            // @@author A0127720M
        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);
        // @@author

        case FindCommand.SHORTCUT:
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case ListCommand.SHORTCUT:
        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.SHORTCUT:
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand(arguments);

        case UndoCommand.SHORTCUT:
        case UndoCommand.COMMAND_WORD:
            return prepareUndo(arguments);

        case RedoCommand.SHORTCUT:
        case RedoCommand.COMMAND_WORD:
            return prepareRedo(arguments);

        case ChangeDirectoryCommand.COMMAND_WORD:
            return prepareChangeDirectory(arguments, config, false);
        case ChangeDirectoryCommand.COMMAND_WORD_SAVE:
            return prepareChangeDirectory(arguments, config, true);
        default:
            return new IncorrectCommand(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    // @@author A0139284X

    /**
     * Parses arguments in the context of the redo task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */

    private Command prepareRedo(String args) {
        Optional<Integer[]> index = parseIndex(args);
        int targetIndex;
        
        if (!index.isPresent()) {
            return new RedoCommand(RedoCommand.DEFAULT_NUMBER_OF_REDO);
        } else {
            targetIndex = Integer.parseInt(index.get().toString());
            return new RedoCommand(targetIndex);
        }
    }

    /**
     * Parses arguments in the context of the undo command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */

    private Command prepareUndo(String args) {
        Optional<Integer[]> index = parseIndex(args);
        int targetIndex;
        
        if (!index.isPresent()) {
            return new UndoCommand(UndoCommand.DEFAULT_NUMBER_OF_UNDO);
        } else {
            targetIndex = Integer.parseInt(index.get()[FIRST_INDEX].toString());
            return new UndoCommand(targetIndex);
        }
    }

    // @@author A0127720M

    /**
     * Parses arguments in the context of the delete marked task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareDeleteMarked(String args) {
        Optional<Integer[]> index = parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        return new DeleteMarkedCommand(index.get());
    }

    /**
     * Parses arguments in the context of the add task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args) {
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        try {
            return new AddCommand(matcher.group("taskName"), matcher.group("startDate"), matcher.group("startTime"),
                    matcher.group("dueDate"), matcher.group("dueTime"), matcher.group("importance"),
                    getTagsFromArgs(matcher.group("tagArguments")));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    /**
     * Extracts the new task's tags from the add command's tag arguments string.
     * Merges duplicate tag strings.
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
        return new HashSet<>(tagStrings);
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {

        Optional<Integer[]> index = parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        return new DeleteCommand(index.get());
    }

    // @@author A0152952A - reused
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
    // @@author

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    // @@author A0142360U
    private Command prepareEdit(String args) {
        final Matcher matcher = EDIT_TASK_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        try {
            return new EditCommand(matcher.group("targetIndex"), matcher.group("taskName"), matcher.group("startDate"),
                    matcher.group("startTime"), matcher.group("dueDate"), matcher.group("dueTime"),
                    matcher.group("importance"), getTagsFromArgs(matcher.group("tagArguments")));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    // @@author A0142360U
    /*
     * Check if argument is empty and execute changeDirectoryCommand if not
     *
     */
    private Command prepareChangeDirectory(String args, Config config, boolean transferTasks) {
        if (args.equals(EMPTY_STRING)) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeDirectoryCommand.MESSAGE_USAGE));
        }
        return new ChangeDirectoryCommand(args, config, transferTasks);
    }

    // @@author

    /**
     * Parses arguments in the context of the select task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareSelect(String args) {
        Optional<Integer[]> index = parseIndex(args);
        int targetIndex;
        
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
        }

        targetIndex = Integer.parseInt(index.get()[FIRST_INDEX].toString());
        return new SelectCommand(targetIndex);
    }

    // @@author A0127720M
    private Command prepareMark(String arguments) {
        Optional<Integer[]> index = parseIndex(arguments);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        return new MarkCommand(index.get());
    }
    
    // @@author A0139284X

    /**
     * Returns the specified index in the {@code command} IF a positive unsigned
     * integer is given as the index. Returns an {@code Optional.empty()}
     * otherwise.
     */
    private Optional<Integer[]> parseIndex(String command) {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(command.trim());
        String[] indexes;
        Integer[] targetIndexes = null;
        int counter = 0;
        
        if (!matcher.matches()) {
            return Optional.empty();
        }

        String index = matcher.group("targetIndex");
        indexes = index.split(TARGET_INDEX_SPLIT);
        
        targetIndexes = new Integer[indexes.length];

        for (String s : indexes) {
            if (!StringUtil.isUnsignedInteger(s)) {
                return Optional.empty();
            }
            targetIndexes[counter] = Integer.parseInt(s);
            counter++;
        }
        
        return Optional.of(targetIndexes);

    }
    
    // @@author

    /**
     * Parses arguments in the context of the find task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }

}

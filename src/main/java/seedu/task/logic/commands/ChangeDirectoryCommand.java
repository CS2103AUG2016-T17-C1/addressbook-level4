package seedu.task.logic.commands;

import java.io.IOException;
import java.util.logging.Logger;

import seedu.task.MainApp;
import seedu.task.alerts.ClearCommandAlert;
import seedu.task.commons.core.Config;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.util.ConfigUtil;
import seedu.task.commons.util.StringUtil;
import seedu.task.model.TaskManager;
import seedu.task.alerts.ChangeDirectoryCommandAlert;

public class ChangeDirectoryCommand extends Command {

    public static final String COMMAND_WORD = "cd";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the location where the tasks in the Task Manager are saved. "
            + "Paramaters: cd {file location}/" + "Example: " + COMMAND_WORD + "  cd data/";

    public static final String MESSAGE_SUCCESS = "Directory Changed";
    public static final String MESSAGE_FAILURE = "Illegal directory name given, please use a different directory name and try again";

    private final String newDirectory;
    private Config newConfig = new Config();
    private String configFilePathUsed;
    private static final String DEFAULT_TASK_MANAGER_XML_FILE_NAME = "taskmanager.xml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Convenience constructor using raw values.
     */
    public ChangeDirectoryCommand(String newDirectory) {
        newDirectory = newDirectory.trim();
        this.newDirectory = newDirectory;
    }

    @Override
    public CommandResult execute() {

        if (isValidDirectory(newDirectory)) {

            newConfig.setTaskManagerFilePath(directoryAddXmlExtension(newDirectory));
            configFilePathUsed = Config.DEFAULT_CONFIG_FILE;
            try {
                ConfigUtil.saveConfig(newConfig, configFilePathUsed);
                displayRestartAlert();
                return new CommandResult(MESSAGE_SUCCESS);

            } catch (IOException e) {
                logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
                return new CommandResult(MESSAGE_FAILURE);
            }
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }

    }

    /*
     * Returns false if directory name contains any illegal characters
     */
    public static boolean isValidDirectory(String directory) {
        if (directory.contains("?")) {
            return false;
        }
        if (!String.valueOf(directory.charAt(directory.length() - 1)).equals("/")) {
            return false;
        }
        return true;
    }

    /*
     * append a "taskmanager.xml" string to the directory name
     */
    public String directoryAddXmlExtension(String directory) {
        return directory + DEFAULT_TASK_MANAGER_XML_FILE_NAME;
    }

    public void displayRestartAlert() {
        if (ChangeDirectoryCommandAlert.changeDirectoryCommand()) {
            System.exit(0);
        } else {
            System.exit(0);
        }

    }

}

package seedu.task.logic;

import javafx.collections.ObservableList;
import seedu.task.commons.core.ComponentManager;
import seedu.task.commons.core.Config;
import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.CommandResult;
import seedu.task.logic.parser.Parser;
import seedu.task.model.Model;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.storage.Storage;

import java.util.logging.Logger;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Parser parser;
    private final Config config;

    public LogicManager(Model model, Storage storage, Config config) {
        this.model = model;
        this.config = config;
        this.parser = new Parser(this.config);
    }


    @Override
    public CommandResult execute(String commandText) throws IllegalValueException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command = parser.parseCommand(commandText);
        command.setData(model);
        return command.execute();
    }

    @Override
    public ObservableList<ReadOnlyTask> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    //@@author A0127720M
	@Override
	public ObservableList<ReadOnlyTask> getFilteredMarkedTaskList() {
		return model.getFilteredMarkedTaskList();
	}
	//@@author
}

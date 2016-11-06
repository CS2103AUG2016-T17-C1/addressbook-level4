package seedu.task.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import seedu.task.MainApp;
import seedu.task.commons.core.LogsCenter;
import seedu.task.model.task.ReadOnlyTask;

//@@author A0142360U
public class CheckTaskAttributes {
    private ReadOnlyTask task;
    public static final String EMPTY_TASK_OBJECT_STRING = "";
    private static final Logger logger = LogsCenter.getLogger(CheckTaskAttributes.class);

    public CheckTaskAttributes(ReadOnlyTask task) {
        this.task = task;
    }

    public boolean startDateExists() {
        if (!this.task.getEventStart().getStartDate().toString().equals(EMPTY_TASK_OBJECT_STRING))
            return true;

        return false;
    }

    public boolean startTimeExists() {
        if (!this.task.getEventStart().getStartTime().toString().equals(EMPTY_TASK_OBJECT_STRING))
            return true;

        return false;
    }

    public boolean endDateExists() {
        if (!this.task.getDeadline().getDueDate().toString().equals(EMPTY_TASK_OBJECT_STRING))
            return true;

        return false;
    }

    public boolean endTimeExists() {
        if (!this.task.getDeadline().getDueTime().toString().equals(EMPTY_TASK_OBJECT_STRING))
            return true;

        return false;
    }

    public boolean checkIfDateHasPassed(String date) {
        LocalDate currentDate = LocalDateTime.now().toLocalDate();
        if (date.equals("")) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate dateToCompare = LocalDate.parse(date, formatter);

            if (currentDate.isBefore(dateToCompare)) {
                return false;
            } else {
                return true;
            }
        } catch (DateTimeParseException exc) {
            logger.warning("invalid date compared");
            throw exc;
        }
    }

    public boolean checkIfDateTimeHasPassed(String dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (dateTime.equals("")) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
            LocalDateTime dateTimeToCompare = LocalDateTime.parse(dateTime, formatter);

            if (currentDateTime.isBefore(dateTimeToCompare)) {
                return false;
            } else {
                return true;
            }
        } catch (DateTimeParseException exc) {
            logger.warning("invalid date compared");
            throw exc;
        }
    }

}

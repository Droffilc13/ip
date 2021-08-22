package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends AddCommand {
    private String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] descriptionDatePair = description.split("/by", 2);
            return tasks.recordDeadline(descriptionDatePair[0].trim(), descriptionDatePair[1].trim());
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("deadline should be in format: [DESCRIPTION] /by [DATE]!\n" +
                    "Only accepted [DATE] format is: date/month/year HHMM (24h time)");
        }
    }
}

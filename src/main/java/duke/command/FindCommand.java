package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;

public class FindCommand implements Command {
    private String word;

    public FindCommand(String description) throws DukeException {
        String[] wordArray = description.split(" ");
        if(wordArray.length > 1) {
            throw new DukeException("I can only find using ONE keyword, you have too many!");
        }
        this.word = wordArray[0];
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.findTasks(word);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

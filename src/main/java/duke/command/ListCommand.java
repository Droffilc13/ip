package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Here is your task list!\n\n" + tasks.printTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

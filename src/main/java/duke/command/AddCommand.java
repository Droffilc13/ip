package duke.command;

public abstract class AddCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }
}
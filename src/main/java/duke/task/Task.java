package duke.task;

import duke.exception.DukeException;

/**
 * <h1> Task </h1>
 * Encapsulates a task that can be marked as done and convert itself
 * to text to be saved and vice versa.
 *
 * @author Clifford
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskSymbol;
    protected static final String DIVIDER = ",";
    protected static final String DONE_STATUS = "[X]";
    protected static final String NOT_DONE_STATUS = "[ ]";

    public Task(String description, String taskSymbol) {
        this.description = description;
        this.isDone = false;
        this.taskSymbol = taskSymbol;
    }

    /**
     * Converts a text from storage file to a Task, otherwise throws DukeException.
     *
     * @param text the text representation of a task in storage file
     * @return the corresponding type of task based on the text provided
     * @throws DukeException if the text representation in storage file does not match any tasks
     */
    public static Task createTaskFromText(String text) throws DukeException {
        String[] taskInformation = text.split(DIVIDER);
        String taskType = taskInformation[0].trim();
        String taskStatus = taskInformation[1].trim();

        switch (taskType) {
        case "[T]":
            Task todo = new Todo(taskInformation[2]);
            changeStatusFromText(todo, taskStatus);
            return todo;
        case "[D]":
            Task deadline = new Deadline(taskInformation[2], taskInformation[3]);
            changeStatusFromText(deadline, taskStatus);
            return deadline;
        case "[E]":
            Task event = new Event(taskInformation[2], taskInformation[3]);
            changeStatusFromText(event, taskStatus);
            return event;
        default:
            throw new DukeException("duke.task.Task symbol from text is not recognised.");
        }
    }

    /**
     * Marks the Task if the text representation of the symbol from save file is the Done status,
     * otherwise leaves Task object unchanged.
     *
     * @param task the Task object
     * @param symbol representation of status symbol in save file.
     * @return the same Task object that has its status updated
     */
    public static Task changeStatusFromText(Task task, String symbol) {
        if (symbol.equals(DONE_STATUS)) {
            task.markAsDone();
        }
        return task;
    }

    public String getTaskSymbol() {
        return taskSymbol;
    }

    public String getDivider() {
        return DIVIDER;
    }

    /**
     * converts a Task object to a formatted text to be saved in storage.
     *
     * @return text representation of Task in storage files.
     */
    public String convertToText() {
        StringBuilder sb = new StringBuilder();
        return sb
            .append(getTaskSymbol())
            .append(DIVIDER)
            .append(getStatusIcon())
            .append(DIVIDER)
            .append(description)
            .toString();
    }

    /**
     * produces a graphical icon of whether a task is done or not.
     *
     * @return a graphical icon of whether a task is done or not
     */
    public String getStatusIcon() {
        return (isDone ? DONE_STATUS : NOT_DONE_STATUS);
    }

    /**
     * Returns true if the task is done and false otherwise.
     *
     * @return true if the task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks a task as done and leaves marked task unchanged.
     *
     * @return a String of whether the task is marked or already marked.
     */
    public String markAsDone() {
        if (isDone) {
            return "Task is already marked as done";
        }
        isDone = true;
        return "Nice! I've marked this task as done:\n  " + this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatusIcon()).append(" ").append(this.description);
        return sb.toString();
    }
}

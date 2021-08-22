package duke.core;

import java.util.ArrayList;

public class Ui {
    private static final String logo =
            " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n";

    private static final String HorizontalSeparator =
            "------------------------------------------------------------------------";

    /**
     * greet is called when the user starts up the program.
     *
     * @return a welcome message when user starts interacting with ChatBot
     */
    public void greetUser() {
        formatDisplay("Hello from\n" + logo +
                "Hello! I'm Chatty Clifford!\nHow may I be of service to you?");
    }

    public void formatDisplay(String input) {
        StringBuilder sb = new StringBuilder();
        StringBuilder formattedSb = sb
                .append(HorizontalSeparator)
                .append("\n")
                .append(input)
                .append("\n")
                .append(HorizontalSeparator);
        System.out.println(formattedSb.toString());
    }
}
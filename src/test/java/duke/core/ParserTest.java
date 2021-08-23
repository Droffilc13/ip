package duke.core;

import duke.command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void identifyCommand_emptyString_exceptionThrown() {
        try {
            assertEquals(null, Parser.identifyCommand(""));
            fail();
        } catch (Exception e) {
            assertEquals("Go on, I'm all ears!", e.getMessage());
        }
    }

    @Test
    public void identifyCommand_validDoneCommandWithoutArgument_exceptionThrown() {
        try {
            assertEquals(null, Parser.identifyCommand("done"));
            fail();
        } catch (Exception e) {
            assertEquals("Don't be shy! I need more information to carry out done :(", e.getMessage());
        }
    }

    @Test
    public void identifyCommand_unrecognisedCommand_exceptionThrown() {
        try {
            Parser.identifyCommand("jksbfaskj bafjkb bjaskfb");
            fail();
        } catch (DukeException e) {
            assertEquals("Sorry, I don't understand what you are saying!",
                    e.getMessage());
        }
    }

    @Test
    public void identifyCommand_validDoneCommand_DoneCommand() {
        try {
            assertEquals(true,
                    Parser.identifyCommand("done 23") instanceof DoneCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void identifyCommand_validDeadlineCommand_DeadlineCommand() {
        try {
            assertEquals(true,
                    Parser.identifyCommand(
                            "deadline walk my cat /by 2/7/2010 1243") instanceof AddDeadlineCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void identifyCommand_validEventCommand_EventCommand() {
        try {
            assertEquals(true,
                    Parser.identifyCommand(
                            "event walk my dog /at 20/8/2019 1200") instanceof AddEventCommand);
        } catch (DukeException e) {
            fail();
        }
    }
}

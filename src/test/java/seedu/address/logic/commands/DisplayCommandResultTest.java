package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DisplayCommandResultTest {
    @Test
    public void constructor_nullPerson_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new DisplayCommandResult("feedback", null));
    }

    @Test
    public void getters() {
        Person displayPerson = new PersonBuilder().build();
        DisplayCommandResult displayCommandResult = new DisplayCommandResult("feedback", displayPerson);
        assertEquals(displayPerson, displayCommandResult.getPerson());
        assertEquals("feedback", displayCommandResult.getFeedbackToUser());
        assertFalse(displayCommandResult.isShowHelp());
        assertFalse(displayCommandResult.isExit());
    }

    @Test
    public void equals() {
        Person displayPerson = new PersonBuilder().build();
        DisplayCommandResult displayCommandResult = new DisplayCommandResult("feedback", displayPerson);

        // same values -> returns true
        assertTrue(displayCommandResult.equals(new DisplayCommandResult("feedback", displayPerson)));

        // same object -> returns true
        assertTrue(displayCommandResult.equals(displayCommandResult));

        // null -> returns false
        assertFalse(displayCommandResult.equals(null));

        // different types -> returns false
        assertFalse(displayCommandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(displayCommandResult.equals(new DisplayCommandResult("different", displayPerson)));

        // different person -> returns false
        Person differentPerson = new PersonBuilder().withName("different").build();
        assertFalse(displayCommandResult.equals(new DisplayCommandResult("feedback", differentPerson)));
    }

    @Test
    public void hashcode() {
        Person displayPerson = new PersonBuilder().build();
        DisplayCommandResult displayCommandResult = new DisplayCommandResult("feedback", displayPerson);

        // same values -> returns same hashcode
        assertEquals(displayCommandResult.hashCode(),
                new DisplayCommandResult("feedback", displayPerson).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(displayCommandResult.hashCode(),
                new DisplayCommandResult("different", displayPerson).hashCode());
    }

    @Test
    public void toStringMethod() {
        Person displayPerson = new PersonBuilder().build();
        DisplayCommandResult displayCommandResult = new DisplayCommandResult("feedback", displayPerson);
        String expected = DisplayCommandResult.class.getCanonicalName() + "{setPerson="
                + displayPerson.toString() + ", feedbackToUser=" + displayCommandResult.getFeedbackToUser()
                + ", showHelp=" + displayCommandResult.isShowHelp()
                + ", exit=" + displayCommandResult.isExit() + "}";
        assertEquals(expected, displayCommandResult.toString());
    }
}

package seedu.address.logic.commands;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents the result of a {@code DisplayCommand} execution.
 * This class is needed because the {@code execute} method in {@code DisplayCommand}
 * needs to return a callback to the UI to display the person.
 */
public class DisplayCommandResult extends CommandResult {
    private final Person setPerson;

    public DisplayCommandResult(String feedbackToUser, Person setPerson) {
        super(feedbackToUser);
        this.setPerson = setPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DisplayCommandResult
                && setPerson.equals(((DisplayCommandResult) other).setPerson)
                && super.equals(other));
    }

    @Override
    public int hashCode() {
        return Objects.hash(setPerson, getFeedbackToUser(), isShowHelp(), isExit());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("setPerson", setPerson.toString())
                .add("feedbackToUser", getFeedbackToUser())
                .add("showHelp", isShowHelp())
                .add("exit", isExit())
                .toString();
    }
}

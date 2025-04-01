package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Displays a person identified using displayed index from the address book.
 */
public class DisplayCommand extends Command {
    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Display the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be positive integer, only 1 index allowed)\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_DISPLAY_PERSON_SUCCESS = "Displayed Person: %1$s";

    private final Index index;

    /**
     * Creates a DisplayCommand to display the person at the specified {@code Index}
     */
    public DisplayCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDisplay = lastShownList.get(index.getZeroBased());

        // return a display command result to display the person
        return new DisplayCommandResult(String.format(
                MESSAGE_DISPLAY_PERSON_SUCCESS,
                Messages.format(personToDisplay)
        ), personToDisplay);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DisplayCommand)) {
            return false;
        }

        DisplayCommand otherDisplayCommand = (DisplayCommand) other;
        return index.equals(otherDisplayCommand.index);
    }

    @Override
    public String toString() {
        return "DisplayCommand{index=" + index + "}";
    }
}

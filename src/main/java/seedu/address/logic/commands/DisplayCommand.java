package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class DisplayCommand extends Command {
    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the persons identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be positive integer, only 1 index allowed)\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_DISPLAY_PERSON_SUCCESS = "Displayed Person: %1$s";

    private final Index index;

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

        // do display action (not implemented yet)
        Person personToDisplay = lastShownList.get(index.getZeroBased());

        return new CommandResult(String.format(
                MESSAGE_DISPLAY_PERSON_SUCCESS,
                Messages.format(personToDisplay)
        ));
    }
}

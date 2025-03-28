package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.GroupingLogic;
import seedu.address.model.Model;

/**
 * Groups all persons in the address book to the user.
 */
public class GroupCommand extends Command {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_SUCCESS = "Grouped all students";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        GroupingLogic.groupStudents(model.getFilteredPersonList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

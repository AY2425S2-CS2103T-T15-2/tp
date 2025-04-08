package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonSimilarity;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the JCRoster+ student list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]... "
            + PREFIX_GRADE + "SUBJECT1:GRADE, SUBJECT2:GRADE, "
            + "SUBJECT3:GRADE, SUBJECT4:GRADE, SUBJECT5:GRADE, SUBJECT6:GRADE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_GRADE + "Math:A, Science:B, English:A, History:C, Geography:B, Music:A";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the JCRoster+ student list!";
    public static final String MESSAGE_SIMILAR_PERSON = "Warning: It is likely that this "
            + "student already exists in the JCRoster+ student list. "
            + "We will add anyways, but please double check. "
            + "You may want to use the edit command or delete command instead.";
    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        this.toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check for duplicate subjects first
        if (!Grade.hasUniqueSixSubjects(toAdd.getGrades())) {
            throw new CommandException(Grade.MESSAGE_DUPLICATE_SUBJECT);
        }

        for (Person person : model.getFilteredPersonList()) {
            PersonSimilarity similarity = person.isSamePerson(toAdd);
            if (similarity.isSame) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
        }

        // Check again for similar persons and add with warning
        for (Person person : model.getFilteredPersonList()) {
            PersonSimilarity similarity = person.isSamePerson(toAdd);
            if (similarity.isLikelySame) {
                model.addPerson(toAdd);
                return new CommandResult(String.format(MESSAGE_SIMILAR_PERSON + "\n" + MESSAGE_SUCCESS,
                        Messages.format(toAdd)));
            }
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        //        if (other == this) {
        //            return true;
        //        }
        //
        //        // instanceof handles nulls
        //        if (!(other instanceof AddCommand)) {
        //            return false;
        //        }
        //
        //        AddCommand otherAddCommand = (AddCommand) other;
        //        return toAdd.equals(otherAddCommand.toAdd);
        return other == this || (other instanceof AddCommand && toAdd.equals(((AddCommand) other).toAdd));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}

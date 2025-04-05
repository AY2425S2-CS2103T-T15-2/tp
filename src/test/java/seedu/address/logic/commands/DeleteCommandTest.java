package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonsAtIndices;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.GroupingLogic;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //    @Test
    //    public void execute_validSingleIndexUnfilteredList_success() {
    //        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        List<Index> indicesToDelete = List.of(INDEX_FIRST_PERSON);
    //        DeleteCommand deleteCommand = new DeleteCommand(indicesToDelete);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
    //                Messages.format(personToDelete));
    //
    //        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    //        expectedModel.deletePerson(personToDelete);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_validMultipleIndicesUnfilteredList_success() {
    //        List<Index> indicesToDelete = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON);
    //        List<Person> personsToDelete = indicesToDelete.stream()
    //                .map(index -> model.getFilteredPersonList().get(index.getZeroBased()))
    //                .toList();
    //
    //        DeleteCommand deleteCommand = new DeleteCommand(indicesToDelete);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
    //                personsToDelete.stream().map(Messages::format).collect(Collectors.joining("\n")));
    //
    //        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    //        personsToDelete.forEach(expectedModel::deletePerson);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        List<Index> indicesToDelete = List.of(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indicesToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validSingleIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Index> indicesToDelete = List.of(INDEX_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indicesToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);
        GroupingLogic.groupStudents(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndicesFilteredList_success() {
        showPersonsAtIndices(model, INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        List<Index> indicesToDelete = List.of(Index.fromZeroBased(0), Index.fromZeroBased(1)); // Pick valid indices
        List<Person> personsToDelete = indicesToDelete.stream()
                .map(index -> model.getFilteredPersonList().get(index.getZeroBased()))
                .toList();

        DeleteCommand deleteCommand = new DeleteCommand(indicesToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                personsToDelete.stream().map(Messages::format).collect(Collectors.joining("\n")));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        personsToDelete.forEach(expectedModel::deletePerson);
        showNoPerson(expectedModel);
        GroupingLogic.groupStudents(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        List<Index> indicesToDelete = List.of(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indicesToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        List<Index> firstList = List.of(INDEX_FIRST_PERSON);
        List<Index> secondList = List.of(INDEX_SECOND_PERSON);

        DeleteCommand deleteFirstCommand = new DeleteCommand(firstList);
        DeleteCommand deleteSecondCommand = new DeleteCommand(secondList);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(firstList);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        List<Index> targetIndices = List.of(Index.fromOneBased(1), Index.fromOneBased(2));
        DeleteCommand deleteCommand = new DeleteCommand(targetIndices);
        String expected = "DeleteCommand{targetIndices=" + targetIndices + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}

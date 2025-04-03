package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Test class for GroupingLogic.
 */
public class GroupingLogicTest {
    
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // Defines the study group for each person in the addressbook according to their overall grade
    private final int[] studyGroups = {1,1,2,3,1,2,3,4,4};

    /**
     * Tests the {@link GroupingLogic#groupStudents(Model)} method to ensure that students are grouped correctly.
     * This test checks that the students in the address book are assigned the correct study group
     * based on their grades.
     * It calls the {@code GroupingLogic.groupStudents()} method to assign groups, and then verifies that each student's
     * assigned study group matches the expected group in the {@code studyGroups} array.
     * The test will pass if all students are placed in the correct group, and fail if any student's
     * study group is incorrect.
     */
    @Test
    public void groupStudents_validStudents_correctGrouping() {
        GroupingLogic.groupStudents(model);
        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            assertEquals(model.getFilteredPersonList().get(i).getStudyGroup(), studyGroups[i]);
        }
    }
}

package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Test class for GroupingLogic.
 */
public class GroupingLogicTest {

    private List<Person> students;

    @BeforeEach
    public void setUp() {
        students = new ArrayList<>();
        students.add(new PersonBuilder().withName("Alice").withGrade(PersonBuilder.DEFAULT_GRADES).build());
        students.add(new PersonBuilder().withName("Bob").withGrade(PersonBuilder.F_GRADES).build());
        students.add(new PersonBuilder().withName("Charlie").withGrade(PersonBuilder.DEFAULT_GRADES).build());
        students.add(new PersonBuilder().withName("David").withGrade(PersonBuilder.A_GRADES).build());
    }

    @Test
    public void groupStudents_validStudents_correctGrouping() {
        GroupingLogic.groupStudents(students);

        // Expected snake draft grouping order (students list is ordered alphabetically)
        assertEquals(2, students.get(0).getStudyGroup()); // Lowest grade (Bob) goes to group 1
        assertEquals(1, students.get(1).getStudyGroup()); // Next student (Alice) goes to group 2
        assertEquals(3, students.get(2).getStudyGroup()); // Next (Charlie) goes to group 3
        assertEquals(4, students.get(3).getStudyGroup()); // Lowest grade (Bob) goes to group 4
    }

    @Test
    public void groupStudents_emptyList_noError() {
        List<Person> emptyList = new ArrayList<>();
        GroupingLogic.groupStudents(emptyList);

        assertEquals(0, emptyList.size()); // Ensure nothing is added or modified
    }
}

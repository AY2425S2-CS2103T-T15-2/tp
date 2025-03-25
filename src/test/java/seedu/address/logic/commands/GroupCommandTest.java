package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.GroupCommand.MESSAGE_SUCCESS;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class GroupCommandTest {
    private Model model;
    private GroupCommand groupCommand;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        groupCommand = new GroupCommand();
    }

    @Test
    public void execute_groupCommand_groupsStudents() {
        // Create sample students
        Person student1 = new PersonBuilder().withName("Alice").withGrade(PersonBuilder.DEFAULT_GRADES).build();
        Person student2 = new PersonBuilder().withName("Bob").withGrade(PersonBuilder.F_GRADES).build();
        Person student3 = new PersonBuilder().withName("Charlie").withGrade(PersonBuilder.DEFAULT_GRADES).build();
        Person student4 = new PersonBuilder().withName("David").withGrade(PersonBuilder.A_GRADES).build();

        model.addPerson(student1);
        model.addPerson(student2);
        model.addPerson(student3);
        model.addPerson(student4);

        // Execute group command
        CommandResult result = groupCommand.execute(model);

        // Verify the success message
        assertEquals(MESSAGE_SUCCESS, result.getFeedbackToUser());

        // Verify that students have been assigned to groups
        List<Person> groupedStudents = model.getFilteredPersonList();
        int[] expectedGroups = {2, 1, 3, 4};
        for (int i = 0; i < groupedStudents.size(); i++) {
            assertEquals(expectedGroups[i], groupedStudents.get(i).getStudyGroup());
        }
    }
}

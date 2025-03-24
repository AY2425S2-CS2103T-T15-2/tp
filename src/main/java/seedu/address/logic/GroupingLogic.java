package seedu.address.logic;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;


/**
 * Groups students into Study Groups
 */
public class GroupingLogic {
    public static final int NUMBEROFGROUPS = 4;

    /**
     * Groups {@code students} in a "snake draft" pattern according to their overall grade
     */
    public static void groupStudents(List<Person> students) {
        List<Person> studentsCopy = new ArrayList<>(students);
        studentsCopy.sort(Person::compareTo);
        int groupIndex = 1;
        int direction = 1;
        for (Person s : studentsCopy) {
            s.setStudyGroup(groupIndex);
            groupIndex += direction;
            if (groupIndex == NUMBEROFGROUPS + 1 || groupIndex == -1) {
                direction = -1;
                groupIndex += direction;
            }
        }
    }
}

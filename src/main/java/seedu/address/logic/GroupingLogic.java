package seedu.address.logic;

import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;

public class GroupingLogic {
    public static final int NUMBEROFGROUPS = 4 ;
    public static void groupStudents(List<Person> students) {
        List<Person> studentsCopy = new ArrayList<>(students);
        studentsCopy.sort(Person::compareTo);
        int groupIndex = 1;
        int direction = 1;
        for (Person s : studentsCopy) {
            s.setStudyGroup(groupIndex);
            groupIndex += direction;
            if (groupIndex == NUMBEROFGROUPS + 1|| groupIndex == -1) {
                direction = -1;
                groupIndex += direction;
            }
        }
    }
}

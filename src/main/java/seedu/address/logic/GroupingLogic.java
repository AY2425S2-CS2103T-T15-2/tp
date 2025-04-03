package seedu.address.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;


/**
 * Handles the logic for grouping students into study groups based on their overall grades.
 */
public class GroupingLogic {
    public static final int NUMBEROFGROUPS = 4;

    /**
     * Groups {@code students} in a "snake draft" pattern according to their overall grade.
     *
     * @param model The model containing the list of students to be grouped.
     */
    public static void groupStudents(Model model) {
        List<Person> studentsCopy = new ArrayList<>(model.getFilteredPersonList());
        studentsCopy.sort(Person::compareTo);
        int[] groupIndex = getGroupIndexes(studentsCopy);

        // Add Studygroup tag to student
        for (int i = 0; i < studentsCopy.size(); i++) {
            Person editedPerson = createStudentWithGroupTag(studentsCopy.get(i), groupIndex[i]);
            model.setPerson(studentsCopy.get(i), editedPerson);
            }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Creates a new {@code Person} with an updated study group tag.
     *
     * @param student The original student whose tags need to be updated.
     * @param groupIndex The study group index to assign to the student.
     * @return A new {@code Person} object with the updated study group tag.
     */
    private static Person createStudentWithGroupTag(Person student, int groupIndex) {
        assert student != null;

        // Edit tags to include study group
        Set<Tag> newTags = new HashSet<>(student.getTags());
        newTags.removeIf(tag -> tag.tagName.contains("Studygroup"));
        newTags.add(new Tag("Studygroup" + groupIndex));

        return new Person(student.getName(), student.getPhone(), student.getEmail(), student.getAddress(),
                student.getRemark(), student.getGrades(), newTags, groupIndex);
    }

    /**
     * Determines the study group indexes for the students based on the snake draft pattern.
     *
     * @param studentsCopy A sorted list of students.
     * @return An array where each index corresponds to the study group assigned to that student.
     */
    private static int[] getGroupIndexes(List<Person> studentsCopy) {
        int[] studyGroupIndexes = new int[studentsCopy.size()];
        int groupIndex = 1;
        int direction = 1;

        for (int i = 0; i < studentsCopy.size(); i++) {
            if (groupIndex == NUMBEROFGROUPS + 1 || groupIndex == 0) {
                direction *= -1;
                groupIndex += direction;
            }
            studyGroupIndexes[i] = groupIndex;
            groupIndex += direction;
        }
        return studyGroupIndexes;
    }
}


package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class GradeTest {
    @Test
    void hasUniqueSixSubjects_nullGrades_returnsFalse() {
        assertFalse(Grade.hasUniqueSixSubjects(null));
    }


    @Test
    void hasUniqueSixSubjects_incorrectLength_returnsFalse() {
        Grade[] tooFewGrades = new Grade[]{
            new Grade("Math", "A"),
            new Grade("Science", "B"),
            new Grade("English", "C"),
            new Grade("History", "D"),
            new Grade("Geography", "E")
        };
        assertFalse(Grade.hasUniqueSixSubjects(tooFewGrades));
    }


    @Test
    void hasUniqueSixSubjects_nullGradeInArray_returnsFalse() {
        Grade[] gradesWithNull = new Grade[]{ new Grade("Math", "A"),
            null,
            new Grade("English", "C"),
            new Grade("History", "D"),
            new Grade("Geography", "E"),
            new Grade("Music", "F")
        };
        assertFalse(Grade.hasUniqueSixSubjects(gradesWithNull));
    }

    @Test
    void hasUniqueSixSubjects_duplicateSubject_returnsFalse() {
        Grade[] duplicateGrades = new Grade[]{
            new Grade("Math", "A"),
            new Grade("MATH", "B"),
            new Grade("English", "C"),
            new Grade("History", "D"),
            new Grade("Geography", "E"),
            new Grade("Music", "F")
        };
        assertFalse(Grade.hasUniqueSixSubjects(duplicateGrades));
    }

    @Test
    void isValidGrade_specialGrades_returnsTrue() {
        assertTrue(Grade.isValidGrade("Math", "S"));
        assertTrue(Grade.isValidGrade("Science", "U"));
        assertTrue(Grade.isValidGrade("English", "s"));
        assertTrue(Grade.isValidGrade("History", "u"));
    }

}

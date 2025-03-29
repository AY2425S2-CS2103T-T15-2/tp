package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
        * Represents a Grade with a subject and a grade value.
        * Ensures that the subject is less than 100 characters and the grade is between A and F.
 */
public class Grade {
    /**
     * Error message to be displayed if the grade constraints are violated.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Subject should be less than 100 characters and grade should be A-F\n"
                    + "Format: subject:grade";

    /**
     * Regular expression to validate the grade value.
     */
    public static final String GRADE_VALIDATION_REGEX = "[A-F]";

    /**
     * Maximum length for the subject.
     */
    public static final int MAX_SUBJECT_LENGTH = 100;

    /**
     * The subject of the grade.
     */
    public final String subject;

    /**
     * The grade value.
     */
    public final String grade;

    /**
     * Constructs a Grade object from a string representation.
     * The string should be in the format "subject:grade".
     *
     * @param gradeString The string representation of the grade.
     * @throws IllegalArgumentException if the string is not in the correct format or the grade is invalid.
     */
    public Grade(String gradeString) {
        requireNonNull(gradeString);
        String[] parts = gradeString.trim().split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        String subject = parts[0].trim();
        String grade = parts[1].trim();
        checkArgument(isValidGrade(subject, grade), MESSAGE_CONSTRAINTS);
        this.subject = subject;
        this.grade = grade;
    }

    /**
     * Constructs a Grade object with the specified subject and grade.
     *
     * @param subject The subject of the grade.
     * @param grade The grade value.
     * @throws IllegalArgumentException if the subject or grade is invalid.
     */
    public Grade(String subject, String grade) {
        requireNonNull(subject);
        requireNonNull(grade);
        checkArgument(isValidGrade(subject, grade), MESSAGE_CONSTRAINTS);
        this.subject = subject;
        this.grade = grade;
    }
    /**
     * Checks if the given subject and grade are valid.
     *
     * @param subject The subject to check.
     * @param grade The grade to check.
     * @return true if the subject and grade are valid, false otherwise.
     */
    public static boolean isValidGrade(String subject, String grade) {
        if (subject == null || grade == null) {
            return false;
        }
        return !subject.isEmpty()
                && subject.length() <= MAX_SUBJECT_LENGTH
                && grade.matches(GRADE_VALIDATION_REGEX);
    }


    /**
     * Returns the string representation of the grade in the format "subject:grade".
     *
     * @return The string representation of the grade.
     */
    @Override
    public String toString() {
        return subject + " : " + grade;
    }


    /**
     * Checks if this grade is equal to another grade.
     *
     * @param other The other grade to compare to.
     * @return true if the grades are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Grade)) {
            return false;
        }

        Grade otherGrade = (Grade) other;
        return subject.equals(otherGrade.subject)
                && grade.equals(otherGrade.grade);
    }


    /**
     * Returns the hash code of the grade.
     *
     * @return The hash code of the grade.
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }


    /**
     * Converts the letter grade to its numeric value.
     * A = 100, B = 60, C = 50, D = 40, E = 30, F = 0
     *
     * @return The numeric value of the grade.
     */
    public double getNumericGrade() {
        switch (this.grade) {
        case "A":
            return 100;
        case "B":
            return 60;
        case "C":
            return 50;
        case "D":
            return 40;
        case "E":
            return 30;
        case "F":
            return 0;
        default:
            throw new IllegalStateException("Invalid grade value: " + this.grade);
        }
    }
}


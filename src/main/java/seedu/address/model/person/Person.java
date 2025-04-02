package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    private static final int NUM_GRADES = 6;
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();
    private final Grade[] grades;
    private int studyGroup;


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Grade[] grades, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags, grades);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.grades = grades.clone();
        this.tags.addAll(tags);
        this.studyGroup = 1;
    }

    public Grade[] getGrades() {
        return grades.clone();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public int getStudyGroup() {
        return studyGroup;
    }


    /**
     * Returns a PersonSimilarity indicating if persons are same or likely the same.
     * This defines a weaker notion of equality between two persons.
     */
    public PersonSimilarity isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return new PersonSimilarity(true, false);
        }

        if (otherPerson == null) {
            return new PersonSimilarity(false, false);
        }

        boolean exactMatch = otherPerson.getName().equals(getName());

        // Check for similarity by comparing alphanumeric characters
        boolean nameSimilar = stripNonAlphanumeric(name.toString())
                .equals(stripNonAlphanumeric(otherPerson.getName().toString()));
        boolean emailSimilar = stripNonAlphanumeric(email.toString())
                .equals(stripNonAlphanumeric(otherPerson.getEmail().toString()));
        boolean phoneSimilar = stripNonAlphanumeric(phone.toString())
                .equals(stripNonAlphanumeric(otherPerson.getPhone().toString()));
        boolean addressSimilar = stripNonAlphanumeric(address.toString())
                .equals(stripNonAlphanumeric(otherPerson.getAddress().toString()));
        boolean gradesSimilar = Arrays.equals(grades, otherPerson.getGrades());
        boolean tagsSimilar = tags.equals(otherPerson.getTags());

        boolean isLikelySame = nameSimilar && emailSimilar && phoneSimilar && addressSimilar
                && gradesSimilar && tagsSimilar;
        boolean isOtherFieldfifferent = !nameSimilar || !emailSimilar || !phoneSimilar || !addressSimilar
                || !gradesSimilar || !tagsSimilar;
        if (exactMatch) {
            if (isOtherFieldfifferent) {
                return new PersonSimilarity(false, true);
            }
            return new PersonSimilarity(true, false);
        }

        if (isLikelySame) {
            return new PersonSimilarity(false, true);
        }
        return new PersonSimilarity(false, false);
    }

    /**
     * Removes all non-alphanumeric characters and converts to lowercase.
     */
    private String stripNonAlphanumeric(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && Arrays.equals(grades, otherPerson.grades);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, Arrays.hashCode(grades), tags, studyGroup);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("grades", Arrays.toString(grades))
                .add("tags", tags)
                .toString();
    }

    /**
     * Calculates and returns the overall grade as an average of all numeric grades.
     *
     * @return The average numeric grade as a double value.
     */
    public double getOverallGrade() {
        if (grades == null || grades.length == 0) {
            return 0.0;
        }

        double sum = 0.0;
        int validGrades = 0;

        for (Grade grade : grades) {
            if (grade != null) {
                sum += grade.getNumericGrade();
                validGrades++;
            }
        }

        return validGrades > 0 ? sum / validGrades : 0.0;
    }

    public int compareTo(Person p) {
        return Double.compare(this.getOverallGrade(), p.getOverallGrade());
    }

    public void setStudyGroup(int group) {
        this.studyGroup = group;
        this.tags.removeIf(tag -> tag.tagName.contains("StudyGroup"));
        this.tags.add(new Tag("StudyGroup" + studyGroup));
    }
}

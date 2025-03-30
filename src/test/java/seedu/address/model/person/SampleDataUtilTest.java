package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.util.SampleDataUtil;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_returnsCorrectData() {
        Person[] persons = SampleDataUtil.getSamplePersons();

        // Check if array is not empty
        assertNotNull(persons);
        assertEquals(6, persons.length);

        // Check first person's details
        Person firstPerson = persons[0];
        assertEquals("Alex Yeoh", firstPerson.getName().toString());
        assertEquals("87438807", firstPerson.getPhone().toString());
        assertEquals("alexyeoh@example.com", firstPerson.getEmail().toString());
        assertEquals("Blk 30 Geylang Street 29, #06-40", firstPerson.getAddress().toString());
        assertTrue(firstPerson.getTags().stream()
                .anyMatch(tag -> tag.tagName.equals("friends")));
    }

    @Test
    public void getSampleAddressBook_returnsNonEmptyAddressBook() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();

        assertNotNull(addressBook);
        assertTrue(addressBook instanceof AddressBook);
        assertEquals(6, addressBook.getPersonList().size());
    }

    @Test
    public void getTagSet_returnsCorrectTags() {
        // Single tag
        var singleTag = SampleDataUtil.getTagSet("friends");
        assertEquals(1, singleTag.size());
        assertTrue(singleTag.stream()
                .anyMatch(tag -> tag.tagName.equals("friends")));

        // Multiple tags
        var multipleTags = SampleDataUtil.getTagSet("friends", "colleagues", "family");
        assertEquals(3, multipleTags.size());
        assertTrue(multipleTags.stream()
                .anyMatch(tag -> tag.tagName.equals("friends")));
        assertTrue(multipleTags.stream()
                .anyMatch(tag -> tag.tagName.equals("colleagues")));
        assertTrue(multipleTags.stream()
                .anyMatch(tag -> tag.tagName.equals("family")));
    }

    @Test
    public void getSampleGrades_returnsCorrectGrades() {
        String gradeString = "Math : A,Science : B,English : A,History : C,Geography : B,Music : A";
        Grade[] grades = SampleDataUtil.getSampleGrades(gradeString);

        assertEquals(6, grades.length);
        assertEquals("Math : A", grades[0].toString());
        assertEquals("Science : B", grades[1].toString());
        assertEquals("English : A", grades[2].toString());
        assertEquals("History : C", grades[3].toString());
        assertEquals("Geography : B", grades[4].toString());
        assertEquals("Music : A", grades[5].toString());
    }

    @Test
    public void getSampleAddressBook_containsAllSamplePersons() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        for (Person person : samplePersons) {
            assertTrue(addressBook.getPersonList().stream()
                    .anyMatch(p -> p.equals(person)));
        }
    }
}

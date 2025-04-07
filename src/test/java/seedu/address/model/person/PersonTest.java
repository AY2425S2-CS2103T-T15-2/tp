package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.DEFAULT_GRADES;
import static seedu.address.testutil.TypicalPersons.NEW_TEST_PERSON;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;



public class PersonTest {
    private Person person;

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {

        // same object -> returns true with no likely similarity
        PersonSimilarity result = ALICE.isSamePerson(ALICE);
        assertTrue(result.isSame);
        assertFalse(result.isLikelySame);

        // null -> returns false with no likely similarity
        result = ALICE.isSamePerson(null);
        assertFalse(result.isSame);
        assertFalse(result.isLikelySame);

        // exact name match but different object -> returns true with no likely similarity
        Person editedAlice = new PersonBuilder(ALICE).withName(ALICE.getName().fullName).build();
        result = ALICE.isSamePerson(editedAlice);
        assertTrue(result.isSame);
        assertFalse(result.isLikelySame);

        // similar name and email but same phonenumber -> returns true with no likely similarity
        editedAlice = new PersonBuilder(ALICE).withName("AlicePauline").withEmail("aliCE@example.com")
                .withPhone("94351253").build();
        result = ALICE.isSamePerson(editedAlice);
        assertFalse(result.isSame);
        assertTrue(result.isLikelySame);

        // different name, but similar (part of uppercase) -> returns false but likely same
        editedAlice = new PersonBuilder(ALICE).withName("Pauline").withEmail("alice@example.com").withPhone("94351253")
                .build();
        result = ALICE.isSamePerson(editedAlice);
        assertFalse(result.isSame);
        assertTrue(result.isLikelySame);

        // name differs in case -> returns false but likely same
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toUpperCase()).build();
        result = BOB.isSamePerson(editedBob);
        assertFalse(result.isSame);
        assertTrue(result.isLikelySame);

        // similar email but different name -> returns false but likely same
        editedAlice = new PersonBuilder(ALICE).withName("Different Name")
                .withEmail("alice@example.com").build();
        result = ALICE.isSamePerson(editedAlice);
        assertFalse(result.isSame);
        assertTrue(result.isLikelySame);

        // same name but different email -> returns false but similar
        editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB)
                .withEmail("alice@example.com").build();
        result = BOB.isSamePerson(editedBob);
        assertFalse(result.isSame);
        assertTrue(result.isLikelySame);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", grades="
                + Arrays.toString(ALICE.getGrades()) + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @BeforeEach
    void setUp() {
        person = new PersonBuilder(ALICE).withGrade(DEFAULT_GRADES).build();
    }


    @Test
    void compareTo() {
        Person higherGradeStudent = new PersonBuilder(NEW_TEST_PERSON).withGrade(PersonBuilder.A_GRADES).build();
        Person lowerGradeStudent = new PersonBuilder(NEW_TEST_PERSON).withGrade(PersonBuilder.F_GRADES).build();

        // Higher grade should be "less than" lower grade in sorting (ascending order)
        assertTrue(higherGradeStudent.compareTo(person) > 0,
                "Higher grade should be less than the default grade.");
        assertTrue(lowerGradeStudent.compareTo(person) < 0,
                "Default grade should be less than lower grade.");
        assertEquals(0, person.compareTo(person),
                "Same person should return 0 in comparison.");
    }
}

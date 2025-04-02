package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STUDYGROUP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final String DEFAULT_GRADES = "Math:A,Science:B,English:A,History:C,Geography:B,Music:A";
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("She likes cats.").withGrade(DEFAULT_GRADES)
            .withTags("Friends", "Studygroup1").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withRemark("He can't sing!")
            .withGrade("Math:A,Science:B,English:A,History:C,Geography:B,Music:A ")
            .withTags("Owesmoney", "Friends", "Studygroup2").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withRemark("He loves ice cream.")
            .withEmail("heinz@example.com").withAddress("wall street").withGrade(DEFAULT_GRADES)
            .withTags("Studygroup3").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withRemark("He runs fast.")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withGrade(DEFAULT_GRADES).withTags("Friends", "Studygroup4").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withRemark("She can dance.")
            .withEmail("werner@example.com").withAddress("michegan ave").withGrade(DEFAULT_GRADES)
            .withTags("Studygroup4").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withRemark("She's a vegetarian.")
            .withEmail("lydia@example.com").withAddress("little tokyo").withGrade(DEFAULT_GRADES)
            .withTags("Studygroup3").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withRemark("He's a vegan.")
            .withEmail("anna@example.com").withAddress("4th street").withGrade(DEFAULT_GRADES)
            .withTags("Studygroup2").build();
    public static final Person HAROLD = new PersonBuilder().withName("Harold")
            .withAddress("someplace").withEmail("harold@example.com")
            .withRemark("She is beautiful.")
            .withPhone("96778766").withGrade(DEFAULT_GRADES)
            .withTags("Enemy", "Studygroup1").build();
    public static final Person NEW_TEST_PERSON = new PersonBuilder().withName("name")
            .withAddress("123, NUS Ave 6, #08-111").withEmail("test@example.com")
            .withRemark("She draws well.")
            .withPhone("12345678").withGrade(DEFAULT_GRADES)
            .withTags("Test", "Studygroup1").build();
    //new test person to test the new add command that has extra component.

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withRemark("He is a lawyer.").withGrade(DEFAULT_GRADES)
            .withTags("Studygroup1").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withRemark("She is a teacher.").withGrade(DEFAULT_GRADES)
            .withTags("Studygroup1").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withRemark(VALID_REMARK_AMY)
            .withGrade(DEFAULT_GRADES).withTags(VALID_TAG_FRIEND, VALID_TAG_STUDYGROUP).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withRemark(VALID_REMARK_BOB)
            .withGrade(DEFAULT_GRADES).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND, VALID_TAG_STUDYGROUP)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE,
                FIONA, GEORGE, HAROLD, NEW_TEST_PERSON));
    }
}

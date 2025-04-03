package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                    getSampleGrades("Math:A,Science:B,English:A,History:C,Geography:B,Music:A"),
                getTagSet("track")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Remark("Good at maths."),
                    getSampleGrades("Math:A,Science:B,English:A,History:C,Geography:B,Music:A"),
                getTagSet("choir", "house")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                    getSampleGrades("Math:A,Science:B,English:A,History:C,Geography:B,Music:A"),
                getTagSet("classrep")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                    getSampleGrades("Math:A,Science:B,English:A,History:C,Geography:B,Music:A"),
                getTagSet("badminton")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                    getSampleGrades("Math:A,Science:B,English:A,History:C,Geography:B,Music:A"),
                getTagSet("studentleader")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                    getSampleGrades("Math:A,Science:B,English:A,History:C,Geography:B,Music:A"),
                getTagSet()),
            new Person(new Name("Lina Tan"), new Phone("88997766"), new Email("linatan@hci.edu.sg"),
                new Address("Hwa Chong Institution Boarding, Block F, Room 121"), EMPTY_REMARK,
                    getSampleGrades("H2 Chemistry:B,H2 Biology:A,H2 Math:A,H1 GP:B,H1 Chinese:A,H1 PW:B"),
                getTagSet("debate", "house")),
            new Person(new Name("Tommy Koh"), new Phone("81234567"), new Email("tommykoh@ri.edu.sg"),
                new Address("Raffles Institution, Block D, Room 234"), EMPTY_REMARK,
                    getSampleGrades("H2 Physics:B,H2 Math:B,H2 Chemistry:C,H1 GP:C,H1 Economics:C,H1 PW:B"),
                getTagSet("scienceclub")),
            new Person(new Name("Nur Aisyah"), new Phone("87878787"), new Email("aisyah@tmjc.edu.sg"),
                new Address("Tampines Meridian JC, Block A, Room 312"), EMPTY_REMARK,
                    getSampleGrades("H2 Biology:A,H2 Chemistry:A,H2 Math:A,H1 GP:A,H1 Malay:A,H1 PW:A"),
                getTagSet("prefect")),
            new Person(new Name("Samuel Tan"), new Phone("83451234"), new Email("samuel@saintandrewsjc.edu.sg"),
                new Address("St. Andrew’s JC, Block B, Room 118"), EMPTY_REMARK,
                    getSampleGrades("H2 Literature:B,H2 History:B,H2 Econs:C,H1 GP:B,H1 Math:B,H1 PW:B"),
                getTagSet("rugby")),
            new Person(new Name("Kim Lee"), new Phone("81231231"), new Email("kimlee@yijc.edu.sg"),
                new Address("Yishun Innova JC, Block E, Room 231"), EMPTY_REMARK,
                    getSampleGrades("H2 Math:A,H2 Physics:B,H2 Chemistry:B,H1 GP:B,H1 Chinese:A,H1 PW:A"),
                getTagSet("house", "volunteer")),
            new Person(new Name("Zachary Lim"), new Phone("85553322"), new Email("zachary@pjc.edu.sg"),
                new Address("Pioneer JC, Block C, Room 101"), EMPTY_REMARK,
                    getSampleGrades("H2 Math:B,H2 Chemistry:A,H2 Physics:A,H1 GP:B,H1 Econs:C,H1 PW:A"),
                getTagSet("basketball")),
            new Person(new Name("Elaine Chua"), new Phone("89998877"), new Email("elaine@dunmanhigh.edu.sg"),
                new Address("Dunman High School, Block G, Room 404"), new Remark("Bad at Chemistry."),
                    getSampleGrades("H2 Biology:A,H2 Chemistry:D,H2 Math:B,H1 GP:A,H1 PW:A,H1 Literature:A"),
                getTagSet("choir")),
            new Person(new Name("Victor Ong"), new Phone("81239876"), new Email("victorong@ajc.edu.sg"),
                new Address("Anderson JC, Block A, Room 213"), EMPTY_REMARK,
                    getSampleGrades("H2 Physics:A,H2 Chemistry:B,H2 Math:A,H1 GP:A,H1 Econs:A,H1 PW:A"),
                getTagSet("scienceclub")),
            new Person(new Name("Natalie Teo"), new Phone("88991122"), new Email("natalieteo@tjc.edu.sg"),
                new Address("Temasek JC, Block E, Room 108"), EMPTY_REMARK,
                    getSampleGrades("H2 Economics:A,H2 Literature:B,H2 History:A,H1 GP:B,H1 Math:B,H1 PW:A"),
                getTagSet("studentcouncil")),
            new Person(new Name("Aaron Goh"), new Phone("85671234"), new Email("aarongoh@hci.edu.sg"),
                new Address("Hwa Chong Institution, Block B, Room 316"), EMPTY_REMARK,
                    getSampleGrades("H2 Math:A,H2 Physics:A,H2 Chemistry:A,H1 GP:A,H1 PW:A,H1 Chinese:B"),
                getTagSet("track")),
            new Person(new Name("Wei Lin"), new Phone("82345678"), new Email("weilin@srjc.edu.sg"),
                new Address("Serangoon JC, Block D, Room 222"), EMPTY_REMARK,
                    getSampleGrades("H2 Literature:C,H2 History:B,H2 Econs:B,H1 GP:C,H1 Math:B,H1 PW:A"),
                getTagSet("volunteer")),
            new Person(new Name("Tracy Tan"), new Phone("87776655"), new Email("tracytan@acjc.edu.sg"),
                new Address("Anglo-Chinese JC, Block F, Room 141"), EMPTY_REMARK,
                    getSampleGrades("H2 Math:A,H2 Physics:B,H2 Chemistry:A,H1 GP:A,H1 PW:A,H1 Economics:A"),
                getTagSet("tennis")),
            new Person(new Name("Dinesh Rao"), new Phone("80001122"), new Email("dineshrao@rjc.edu.sg"),
                new Address("Raffles Institution, Block E, Room 114"), EMPTY_REMARK,
                    getSampleGrades("H2 Math:A,H2 Physics:A,H2 Chemistry:B,H1 GP:B,H1 PW:A,H1 Tamil:A"),
                getTagSet("drama")),
            new Person(new Name("Farah Binte Osman"), new Phone("86664422"), new Email("farah@vjc.edu.sg"),
                new Address("Victoria JC, Block C, Room 330"), EMPTY_REMARK,
                    getSampleGrades("H2 Bio:A,H2 Chem:B,H2 Math:A,H1 GP:B,H1 Malay:A,H1 PW:A"),
                getTagSet("house"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Grade[] getSampleGrades(String grades) {
        return Arrays.stream(grades.split(","))
                .map(Grade::new)
                .toArray(Grade[]::new);
    }


}

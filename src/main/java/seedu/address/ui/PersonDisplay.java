package seedu.address.ui;

import java.util.Arrays;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * Displays information of a {@code Person} only when you click onto the person.
 * Displays on the right side panel of the application.
 */
public class PersonDisplay extends UiPart<Region> {
    private static final String FXML = "PersonDisplay.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    // When updating person display, call PersonDisplay constructor again with the updated person
    public Person person;

    @FXML
    private VBox cardDisplay;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    // @FXML
    // private FlowPane grades;

    /**
     * Creates an empty {@code PersonCode} to display as a placeholder.
     */
    public PersonDisplay() {
        super(FXML);
        this.person = null;
        id.setText("");
        setLabelWrap(name, "Double click on a person to display their full profile");
        phone.setText("");
        address.setText("");
        email.setText("");
    }

    /**
     * Sets the {@code Person} to be displayed.
     */
    public void setPerson(Person person) {
        this.person = person;
        setLabelWrap(name,"Name: " + person.getName().fullName);
        setLabelWrap(phone, "Phone: " + person.getPhone().value);
        setLabelWrap(address, "Address: " + person.getAddress().value);
        setLabelWrap(email, "Email: " + person.getEmail().value);
        tags.getChildren().clear();
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        // Complete grades function later
        // Arrays.stream(person.getGrades())
        //         .forEach(grade -> grades.getChildren().add(new Label(grade.toString())));
    }

    /**
     * Sets text wrap attribute to every {@code Label}  based on {@code guiSettings}.
     */
    private static void setLabelWrap(Label label, String title) {
        label.setText(title);
        label.setWrapText(true);
    }
}

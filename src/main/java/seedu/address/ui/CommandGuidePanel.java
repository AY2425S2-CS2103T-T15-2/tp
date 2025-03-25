package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Command guide component that is displayed on the left hand side of the main window.
 */
public class CommandGuidePanel extends UiPart<Region> {
    private static final String FXML = "CommandGuidePlaceholder.fxml";
    private static final String COMMAND_GUIDE_TITLE = "Command Guide";
    private static final String COMMAND_GUIDE_DESCRIPTION =
            "These are the list of commands available in the application.";
    private static final String ADD_COMMAND =
            "Add a person: add n/NAME p/PHONE e/EMAIL a/ADDRESS g/SUBJECT1:GRADE...SUBJECT6:GRADE [t/TAG]";
    private static final String CLEAR_COMMAND = "Clear all persons: clear";
    private static final String DELETE_COMMAND = "Delete a person: delete INDEX1 [INDEX2 ...] ,";
    private static final String EDIT_COMMAND =
            "Edit a person: edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] "
                    + "[g/SUBJECT1:GRADE...SUBJECT6:GRADE] [t/TAG]";
    private static final String EXIT_COMMAND = "Exit the application: exit";
    private static final String FIND_COMMAND = "Find persons by name: find KEYWORD [MORE_KEYWORDS]";
    private static final String HELP_COMMAND = "Open help window: help";
    private static final String LIST_COMMAND = "List all persons: list";
    private static final String FILTER_COMMAND = "Filter persons by tag: filter KEYWORD [MORE_KEYWORDS]";
    private static final String REMARK_COMMAND = "Edit the remark of a person: remark INDEX r/REMARK";

    @FXML
    private VBox commandGuidePlaceholder;

    @FXML
    private Label commandGuideTitle;

    @FXML
    private Label commandGuideDescription;

    @FXML
    private Label addCommand;

    @FXML
    private Label clearCommand;

    @FXML
    private Label deleteCommand;

    @FXML
    private Label editCommand;

    @FXML
    private Label exitCommand;

    @FXML
    private Label findCommand;

    @FXML
    private Label helpCommand;

    @FXML
    private Label listCommand;

    @FXML
    private Label filterCommand;

    @FXML
    private Label remarkCommand;

    /**
     * Creates a {@code CommandGuidePlaceholder} with hardcoded commands.
     */
    public CommandGuidePanel() {
        super(FXML);
        setLabelWrap(commandGuideTitle, COMMAND_GUIDE_TITLE);
        setLabelWrap(commandGuideDescription, COMMAND_GUIDE_DESCRIPTION);
        setLabelWrap(addCommand, ADD_COMMAND);
        setLabelWrap(clearCommand, CLEAR_COMMAND);
        setLabelWrap(deleteCommand, DELETE_COMMAND);
        setLabelWrap(editCommand, EDIT_COMMAND);
        setLabelWrap(exitCommand, EXIT_COMMAND);
        setLabelWrap(findCommand, FIND_COMMAND);
        setLabelWrap(helpCommand, HELP_COMMAND);
        setLabelWrap(listCommand, LIST_COMMAND);
        setLabelWrap(filterCommand, FILTER_COMMAND);
        setLabelWrap(remarkCommand, REMARK_COMMAND);
    }

    private static void setLabelWrap(Label label, String title) {
        label.setText(title);
        label.setWrapText(true);
    }
}

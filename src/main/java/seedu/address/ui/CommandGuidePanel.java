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
    private static final String[] ADD_COMMAND = {
        "Add a person: ", "add n/NAME p/PHONE e/EMAIL a/ADDRESS g/SUBJECT1:GRADE...SUBJECT6:GRADE [t/TAG]"
    };
    private static final String[] CLEAR_COMMAND = {
        "Clear all persons: ", "clear"
    };
    private static final String[] DELETE_COMMAND = {
        "Delete a person: ", "delete INDEX1 [INDEX2 ...] ,"
    };
    private static final String[] DISPLAY_COMMAND = {
        "Display a person: ", "display INDEX"
    };
    private static final String[] EDIT_COMMAND = {
        "Edit a person: ",
            "edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/SUBJECT1:GRADE ... SUBJECT6:GRADE] [t/TAG]"
    };
    private static final String[] EXIT_COMMAND = {
        "Exit the application: ", "exit"
    };
    private static final String[] FIND_COMMAND = {
        "Find persons by name: ", "find KEYWORD [MORE_KEYWORDS]"
    };
    private static final String[] HELP_COMMAND = {
        "Open help window: ", "help"
    };
    private static final String[] LIST_COMMAND = {
        "List all persons: ", "list"
    };
    private static final String[] FILTER_COMMAND = {
        "Filter persons by tag: ", "filter KEYWORD [MORE_KEYWORDS]"
    };
    private static final String[] REMARK_COMMAND = {
        "Edit the remark of a person: ", "remark INDEX r/REMARK"
    };
    private static final String[] GROUP_COMMAND = {
        "Group students into 4 groups: ", "group"
    };

    @FXML
    private VBox commandGuidePlaceholder;
    @FXML
    private Label commandGuideTitle;
    @FXML
    private Label addCommand;
    @FXML
    private Label addDesc;
    @FXML
    private Label clearCommand;
    @FXML
    private Label clearDesc;
    @FXML
    private Label deleteCommand;
    @FXML
    private Label deleteDesc;
    @FXML
    private Label displayCommand;
    @FXML
    private Label displayDesc;
    @FXML
    private Label editCommand;
    @FXML
    private Label editDesc;
    @FXML
    private Label exitCommand;
    @FXML
    private Label exitDesc;
    @FXML
    private Label findCommand;
    @FXML
    private Label findDesc;
    @FXML
    private Label helpCommand;
    @FXML
    private Label helpDesc;
    @FXML
    private Label listCommand;
    @FXML
    private Label listDesc;
    @FXML
    private Label filterCommand;
    @FXML
    private Label filterDesc;
    @FXML
    private Label remarkCommand;
    @FXML
    private Label remarkDesc;
    @FXML
    private Label groupCommand;
    @FXML
    private Label groupDesc;

    /**
     * Creates a {@code CommandGuidePlaceholder} with hardcoded commands.
     */
    public CommandGuidePanel() {
        super(FXML);
        commandGuideTitle.setText(COMMAND_GUIDE_TITLE);
        commandGuideTitle.setWrapText(true);

        setLabels(addCommand, addDesc, ADD_COMMAND);
        setLabels(clearCommand, clearDesc, CLEAR_COMMAND);
        setLabels(deleteCommand, deleteDesc, DELETE_COMMAND);
        setLabels(displayCommand, displayDesc, DISPLAY_COMMAND);
        setLabels(editCommand, editDesc, EDIT_COMMAND);
        setLabels(exitCommand, exitDesc, EXIT_COMMAND);
        setLabels(findCommand, findDesc, FIND_COMMAND);
        setLabels(helpCommand, helpDesc, HELP_COMMAND);
        setLabels(listCommand, listDesc, LIST_COMMAND);
        setLabels(filterCommand, filterDesc, FILTER_COMMAND);
        setLabels(remarkCommand, remarkDesc, REMARK_COMMAND);
        setLabels(groupCommand, groupDesc, GROUP_COMMAND);
    }

    private static void setLabels(Label label1, Label label2, String[] commands) {
        label1.setText(commands[0]);
        label1.setWrapText(true);
        label1.setUnderline(true);

        label2.setText(commands[1]);
        label2.setWrapText(true);
    }
}

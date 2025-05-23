---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# JCRoster+ Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is a brownfield software developed based on [AddressBook Level-3](https://github.com/nus-cs2103-AY2425S2/tp), provided by the CS2103T teaching team.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

**Note:** The terms *“Person”* and *“Student”* refer to the same entity and are used interchangeably throughout this guide and the application. Similarly, in the Design and Implementation sections, the term *“AddressBook”* has been retained instead of *“JCRoster+”* to remain consistent with the existing file and class names in the codebase.

<div style="page-break-after: always;"></div>

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter`, `Person Display`, `Command Guide Panel` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>

<div style="page-break-after: always;"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**: Junior College Homeroom Teacher

**Value proposition**:

* Efficient Contact Management: Provides quick access to student details for Junior College homeroom teachers.
* Smart Tagging System: Enables quick categorization and seamless student grouping based on academic strengths.
* Administrative Convenience: Reduces the hassle of managing student information and classroom interactions.
* Improved Efficiency: Streamlines administrative tasks, allowing teachers to focus more on student engagement.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                | I want to …​                                   | So that I can…​                                                       |
|----------|------------------------|-----------------------------------------------|------------------------------------------------------------------------|
| `* * *`  | JC Homeroom Teacher     | search for a student by name                  | quickly retrieve their information                                     |
| `* * *`  | JC Homeroom Teacher     | add a new contact                             | keep track of their contact details efficiently                        |
| `* * *`  | JC Homeroom Teacher     | delete a contact                              | remove entries that I no longer need                                   |
| `* * *`  | JC Homeroom Teacher     | exit the program                              | close the application when I am done using it                          |
| `* * *`  | JC Homeroom Teacher     | list all students                             | view the entire class roster at once                                   |
| `* * *`  | JC Homeroom Teacher     | edit a student’s information                  | update details like phone, email, and grades                           |
| `* * *`  | JC Homeroom Teacher     | add complex names (e.g., with s/o, d/o)       | accommodate real-world naming formats                                  |
| `* * *`  | JC Homeroom Teacher     | be warned when adding a student with duplicate name | avoid accidental duplicates while allowing flexibility          |
| `* * *`  | JC Homeroom Teacher     | group students into 4 balanced study groups   | create fair and effective groupings for collaboration                  |
| `* * *`  | JC Homeroom Teacher     | automatically regroup students when grades change | keep groupings fair without manual effort                        |
| `* * *`  | JC Homeroom Teacher     | view a student’s study group via tag          | identify their assigned group easily                                   |
| `* *`    | JC Homeroom Teacher     | filter students based on tags                 | view specific categories (e.g., student leaders)                       |
| `* *`    | JC Homeroom Teacher     | leave remarks for students                    | record notes like progress or follow-up actions                        |
| `*`      | JC Homeroom Teacher     | clear all student entries                     | reset the roster when needed                                           |
| `*`      | JC Homeroom Teacher     | view a help guide                             | understand how to use the app effectively                              |

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is the `JCRoster+` and the **Actor** is the `JC Homeroom Teacher`, unless specified otherwise)

**Use case: Delete a person**

**MSS**

1.  JCRoster+ shows a list of persons.
2.  User requests to delete specific person(s) in the list.
3.  JCRoster+ deletes the person(s).

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. JCRoster+ shows an error message.

      Use case resumes at step 1.

**Use case: Add a person**

**MSS**

1. JCRoster+ prompts the user to enter student details (name, phone number, email, address, grades, and optional tags).
2. User enters the details in the specified format.
3. JCRoster+ validates the input. 
4. JCRoster+ adds the person to the address book and displays a success message.

    Use case ends.

**Extensions**

* 2a. User enters an invalid name.

    * 2a1. JCRoster+ shows an error message.

      Use case resumes at step 1.
* 2b. User enters an invalid phone number (less than 3 or more than 15 digits).

    * 2b1. JCRoster+ shows an error message.

      Use case resumes at step 1.

* 2c. User enters an invalid email (incorrect format).

    * 2c1. JCRoster+ shows an error message.

      Use case resumes at step 1.

**Use case: Find a person**

**MSS**

1. JCRoster+ prompts the user to enter a name or keyword to search.
2. User enters the search query.
3. JCRoster+ searches the student list for matching names.
4. JCRoster+ displays a list of matching persons, if found.

    Use case ends.

**Extensions**

* 3a. No matches are found.

    * 3a1. JCRoster+ displays an empty list.
      Use case resumes at step 1.

**Use case: Edit a student**

**MSS**

1.  JCRoster+ shows a list of students.
2.  User requests to edit a student’s details using the edit command, specifying the student’s index and one or more fields to update.
3.  JCRoster+ validates the input.
4.  JCRoster+ updates the student’s information and displays a success message.

    Use case ends.

**Extensions**

* 1a. The list is empty.

    * 1a1. JCRoster+ shows an error message.  
      Use case ends.

* 2a. The given index is invalid.

    * 2a1. JCRoster+ shows an error message.  
      Use case resumes at step 1.

* 2b. No fields are provided for update.

    * 2b1. JCRoster+ shows an error message.  
      Use case resumes at step 1.

* 3a. One or more input fields are invalid (e.g., invalid phone number, email, or grade format).

    * 3a1. JCRoster+ shows an error message.  
      Use case resumes at step 1.

**Use case: Add/Edit/Remove a remark for a student**

**MSS**

1.  JCRoster+ shows a list of students.
2.  User requests to add, edit, or remove a remark for a specific student using the `remark` command with a specified index and remark content.
3.  JCRoster+ validates the input.
4.  JCRoster+ updates the student’s remark and displays a success message.

    Use case ends.

**Extensions**

* 1a. The list is empty.

    * 1a1. JCRoster+ shows an error message.  
      Use case ends.

* 2a. The given index is invalid.

    * 2a1. JCRoster+ shows an error message.  
      Use case resumes at step 1.

* 2b. The remark prefix `r/` is missing or malformed.

    * 2b1. JCRoster+ shows an error message.  
      Use case resumes at step 1.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should handle unexpected user inputs/system crashes gracefully by providing helpful error messages and not losing data.
5. Software should work without requiring an installer.
6. Data should be stored in a format that is easy to read and edit manually, in case the user wants to do so.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **JC Homeroom Teacher**: Singapore Junior College Homeroom/Form Teachers
* **Student contact details**: Refers to name, email, phone number, address

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Run it using the command 'java -jar jcroster+.jar' Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

**Team Size:** 4

### 1. UI Enhancement – Full Visibility of Command Guide Panel

Currently, the visibility of the Command Guide Panel may be limited depending on window size or layout constraints. We plan to enhance the user interface by ensuring that the Command Guide Panel is always fully visible, regardless of window state or resolution. This will improve usability by making the available commands consistently accessible to the user, reducing confusion and enhancing overall user experience.

### 2. UI Enhancement - Improve Responsiveness of Person Display Panel

Currently, the Person Display Panel takes in a Person object which is then displayed in the panel. If you use any command afterward that updates the state of the already displayed person, you will have to run the 'display' command again to see the updated information. We plan to improve this by ensuring that the Person Display Panel is always updated with the latest information of the displayed person. This will enhance the user experience by providing real-time updates and reducing the need for manual refreshes.

### 3. Feature Enhancement – Support for Multi-word Tags

Currently, tags are limited to single words, which restricts expressiveness when categorizing students. We plan to enhance the tagging functionality to support multi-word tags (e.g., “Needs Follow-Up”, “High Achiever”). This will allow users to add more descriptive and meaningful tags, improving the clarity and usefulness of student categorization.

### 4. Behavioural Enhancement – Preserve Displayed Subset After Command Execution

At present, the student list resets to display all students after each command, regardless of whether a filtered subset was shown prior. We plan to enhance this by retaining the previously displayed subset. For instance, if the user filters the list using the `find` command and then executes a `remark` command, the filtered subset will remain visible instead of reverting to the full list. This improvement ensures smoother workflows and a more intuitive user experience during focused tasks.

### 5. Data Validation Enhancement – Subject Field Input Checking

Currently, the application accepts any input in the subject fields, which may lead to inconsistent or invalid data. We plan to introduce validation that cross-checks subject entries against a predefined list of subjects offered at junior colleges (JCs). This enhancement will improve data integrity and prevent erroneous or unsupported subject entries, ensuring greater reliability of student academic records.

### 6. Usability Enhancement – More Specific and Actionable Error Messages

Currently, some of the application's error messages are general and may not provide users with enough information to understand what went wrong or how to fix it. We plan to enhance the user experience by replacing vague error messages with more specific and contextual ones that clearly indicate the cause of the error and suggest possible corrective actions. This will help users recover more efficiently from mistakes and reduce frustration during usage.

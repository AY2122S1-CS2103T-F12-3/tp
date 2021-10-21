package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tutee.Tutee;

/**
 * An UI component that displays information of a {@code Tutee}.
 */
public class TuteeCard extends UiPart<Region> {

    private static final String FXML = "TuteeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tutee tutee;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label overdue;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label level;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TuteeCode} with the given {@code Tutee} and index to display.
     */
    public TuteeCard(Tutee tutee, int displayedIndex) {
        super(FXML);
        requireNonNull(tutee);
        this.tutee = tutee;
        id.setText(displayedIndex + ". ");
        name.setText(tutee.getName().fullName);
        if (tutee.getPayment().isOverdue) {
            overdue.setVisible(true);
        }
        phone.setText(tutee.getPhone().value);
        address.setText(tutee.getAddress().value);
        level.setText(tutee.getLevel().stringRepresentation);
        tutee.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuteeCard)) {
            return false;
        }

        // state check
        TuteeCard card = (TuteeCard) other;
        return id.getText().equals(card.id.getText())
                && tutee.equals(card.tutee);
    }
}

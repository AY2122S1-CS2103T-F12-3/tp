package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.TrackO;
import seedu.address.model.Model;

/**
 * Clears Track-O.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the list of tutees, "
            + "replacing it with an empty list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Track-O has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTrackO(new TrackO());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

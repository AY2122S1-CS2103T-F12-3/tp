package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Gets a person identified using it's displayed index from the address book and
 * displays additional information on console
 */
public class GetCommand extends Command {

    public static final String COMMAND_WORD = "get";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Obtains additional information on the person identified by the index "
            + "number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    //Displays additional information field of individual
    public static final String MESSAGE_GET_PERSON_SUCCESS = "Retrieved Tutee: %1$s\n"
            + "Remarks: [To be added with remark field]";

    private final Index targetIndex;

    public GetCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person retrievedPerson = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_GET_PERSON_SUCCESS, retrievedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetCommand // instanceof handles nulls
                && targetIndex.equals(((GetCommand) other).targetIndex)); // state check
    }
}
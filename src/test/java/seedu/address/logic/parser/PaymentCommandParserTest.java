package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_RECV_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_AMOUNT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_LESSON_INDEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_RECV_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_INDEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_AMOUNT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_RECV_DATE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TUTEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TUTEE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.paymentcommand.PaymentAddCommand;
import seedu.address.logic.commands.paymentcommand.PaymentCommand;
import seedu.address.logic.commands.paymentcommand.PaymentReceiveCommand;
import seedu.address.logic.commands.paymentcommand.PaymentSetAmountCommand;
import seedu.address.logic.commands.paymentcommand.PaymentSetDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutee.Payment;


public class PaymentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaymentCommand.MESSAGE_USAGE_ALL);

    private PaymentCommandParser parser = new PaymentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" , MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" , MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_PAYMENT_AMOUNT_DESC,
                Payment.MESSAGE_CONSTRAINTS); // invalid payment amount
        // assertParseFailure(parser, "1" + INVALID_LESSON_INDEX_DESC,
        //        Phone.MESSAGE_CONSTRAINTS); // invalid lesson index
        assertParseFailure(parser, "1" + INVALID_PAYMENT_DATE_DESC,
                Payment.DATE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_PAYMENT_RECV_DATE_DESC,
                Payment.DATE_CONSTRAINTS); // invalid date
    }

    /**
     * Test should fail when provided multiple prefixes e.g payment 1 lesson/1 amount/100
     */
    @Test
    public void parse_multipleValues_failure() {
        String failureMsg = String.format(MESSAGE_INVALID_FORMAT,
                PaymentCommand.MESSAGE_PAYMENT_MANAGEMENT_USAGE);
        assertParseFailure(parser, "1" + PAYMENT_LESSON_INDEX_AMY
                + PAYMENT_AMOUNT_DESC_AMY, failureMsg);
        assertParseFailure(parser, "1" + PAYMENT_DATE_DESC_AMY
                + PAYMENT_AMOUNT_DESC_AMY, failureMsg);
    }

    @Test
    public void parse_setPaymentAmountCommand_success() {
        Index targetIndex = INDEX_THIRD_TUTEE;
        String userInput = targetIndex.getOneBased() + PAYMENT_AMOUNT_DESC_AMY;
        PaymentCommand expectedCommand = new PaymentSetAmountCommand(targetIndex, VALID_PAYMENT_AMOUNT_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_setPaymentDateCommand_success() throws ParseException {
        Index targetIndex = INDEX_SECOND_TUTEE;
        String userInput = targetIndex.getOneBased() + PAYMENT_DATE_DESC_AMY;
        LocalDate paymentDateAmy = ParserUtil.parsePayByDate(VALID_PAYMENT_DATE_AMY);
        PaymentCommand expectedCommand = new PaymentSetDateCommand(targetIndex,
                paymentDateAmy);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    // Testing for when recv date is empty, to be included in once we allow parsing of null values into /receive
    // @Test
    // public void parse_receivePaymentCommand_empty_success() throws ParseException {
    //    Index targetIndex = INDEX_FIRST_TUTEE;
    //    String userInput = targetIndex.getOneBased() + PAYMENT_RECV_DATE_DESC_BOB;
    //    LocalDate PAYMENT_RECV_DATE_BOB = ParserUtil.parsePayByDate(VALID_PAYMENT_RECV_DATE_BOB);
    //    PaymentCommand expectedCommand = new PaymentReceiveCommand(targetIndex, PAYMENT_RECV_DATE_BOB);
    //    assertParseSuccess(parser, userInput, expectedCommand);
    // }

    // Testing for when recv date is non-empty
    @Test
    public void parse_receivePaymentCommand_success() throws ParseException {
        Index targetIndex = INDEX_SECOND_TUTEE;
        String userInput = targetIndex.getOneBased() + PAYMENT_RECV_DATE_DESC_AMY;
        LocalDate paymentRecvDateAmy = ParserUtil.parsePayByDate(VALID_PAYMENT_RECV_DATE_AMY);
        PaymentCommand expectedCommand = new PaymentReceiveCommand(targetIndex, paymentRecvDateAmy);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addPaymentCommand_success() {
        Index targetIndex = INDEX_SECOND_TUTEE;
        String userInput = targetIndex.getOneBased() + PAYMENT_LESSON_INDEX_AMY;
        PaymentCommand expectedCommand = new PaymentAddCommand(targetIndex, VALID_LESSON_INDEX_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_basicPaymentCommand_success() {
        Index targetIndex = INDEX_THIRD_TUTEE;
        String userInput = targetIndex.getOneBased() + "";
        PaymentCommand expectedCommand = new PaymentCommand(targetIndex);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

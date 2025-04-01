package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DisplayCommand;

public class DisplayCommandParserTest {
    private DisplayCommandParser parser = new DisplayCommandParser();

    @Test
    public void parse_validSingleIndex_returnsDisplayCommand() {
        Index index = INDEX_FIRST_PERSON;
        assertParseSuccess(parser, "1", new DisplayCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_exceedMaxLimit_throwsParseException() {
        assertParseFailure(parser, "1 2 3 4 5 6 7 8 9 10 11",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayCommand.MESSAGE_USAGE));
    }
}

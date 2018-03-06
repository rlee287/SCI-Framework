package sci.utilities;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import scilib.utilities.Tokenizer;

/**
 * A collection of tests for the Tokenizer utility.
 * @author Auxiliatrix
 *
 */
public class TokenizerTest {
	
	@Test
	public void tokenizerParse() {
        Tokenizer t = new Tokenizer();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        expected.add("E");
        expected.add("F");
        expected.add("G");
        expected.add("H");
        expected.add("I");
        expected.add("J");
        expected.add("K");
        assertEquals(expected, t.parse("A B \"C\" \"D\" E\"F\" G\"H\" I\"J\"K"));

	}
}

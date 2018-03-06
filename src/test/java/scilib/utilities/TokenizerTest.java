package scilib.utilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;

/**
 * A collection of tests for the Tokenizer utility.
 * @author Auxiliatrix
 * @author rlee287
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

	@Test
	public void testParse() {
		Tokenizer t = new Tokenizer();
		ArrayList<String> arr = t.parse("A string of words");
		Iterator<String> iter = arr.iterator();
		assertEquals("A", iter.next());
		assertEquals("string", iter.next());
		assertEquals("of", iter.next());
		assertEquals("words", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testParseWithQuotations() {
		Tokenizer t = new Tokenizer();
		ArrayList<String> arr = t.parse("A \"\" \"space\" \"delimited\" emptiness \"\" ");
		Iterator<String> iter = arr.iterator();
		assertEquals("A", iter.next());
		assertEquals("space", iter.next());
		assertEquals("delimited", iter.next());
		assertEquals("emptiness", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testParseWithSpacePadding() {
		Tokenizer t = new Tokenizer();
		ArrayList<String> arr = t.parse("         TABS FIRST tariff?       ");
		Iterator<String> iter = arr.iterator();
		assertEquals("TABS", iter.next());
		assertEquals("FIRST", iter.next());
		assertEquals("tariff?", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testParseWithQuotationsConnected() {
		Tokenizer t = new Tokenizer();
		ArrayList<String> arr = t.parse("A \"\" \"space \" begin\"delimited\"emptiness \"\" ");
		Iterator<String> iter = arr.iterator();
		assertEquals("A", iter.next());
		assertEquals("space ", iter.next());
		assertEquals("begin", iter.next());
		assertEquals("delimited", iter.next());
		assertEquals("emptiness", iter.next());
		assertFalse(iter.hasNext());
	}

}

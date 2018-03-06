package scilib.utilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;

public class TokenizerTest {

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

package scilib.utilities;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser which converts a String into an ArrayList of String 'words,' delimited by spaces.
 * @author Squeakadoodle
 *
 */
public class Tokenizer {
	
	/**
	 * Construct a Tokenizer object.
	 */
	public Tokenizer() {}
	
	/**
	 * Convert a String into an ArrayList of its space-delimited components.
	 * @param line String to be converted.
	 * @return ArrayList of Strings of the space-delimited 'words' from the input String.
	 */
	public ArrayList<String> tokenize(String line) {
		ArrayList<String> tokens = new ArrayList<String>();
		@SuppressWarnings("resource")
		Scanner t = new Scanner(line);
		t.useDelimiter(" ");
		while( t.hasNext() ) {
			tokens.add(t.next());
		}
		return tokens;
	}
	
}

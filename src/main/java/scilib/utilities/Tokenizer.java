package scilib.utilities;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Parser which converts a String into an ArrayList of String 'words,' delimited by spaces.
 * @author Auxiliatrix
 *
 */
public class Tokenizer {
	
	/**
	 * Creates a Tokenizer object.
	 */
	public Tokenizer() {}
	
	/**
	 * Converts a String into an ArrayList of its space-delimited components.
	 * @param line - String to be converted
	 * @return the space-delimited 'words' from the input String.
	 */
	public ArrayList<String> tokenize(String line) {
		ArrayList<String> tokens = new ArrayList<String>();
		StringTokenizer t = new StringTokenizer(line);
		while( t.hasMoreTokens() ) {
			tokens.add(t.nextToken(" "));
		}
		return tokens;
	}
	
}

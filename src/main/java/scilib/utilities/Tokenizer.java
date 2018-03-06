package scilib.utilities;

import java.util.ArrayList;

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
	 * Converts a String into an ArrayList of its bash-style delimited components.
	 * @param line - String to be converted
	 * @return the bash-style delimited 'words' from the input String.
	 */
	public ArrayList<String> parse(String line) {
		ArrayList<String> tokens = new ArrayList<String>();
		line += " ";
		char[] charArray = line.toCharArray();
		String temp = "";
		boolean openQuote = false;
		for( char c : charArray ) {
			if( c == ' ' && !openQuote ) {
				if( !temp.isEmpty() ) {
					tokens.add(temp);
					temp = "";
				}
			} else if( c == '"' ) {
				if( openQuote ) {
					openQuote = false;
					if( temp.length() > 0 ) {
						tokens.add(temp);
					}
					temp = "";
				} else {
					if( !temp.isEmpty() ) {
						tokens.add(temp);
						temp = "";
					}
					openQuote = true;
				}
			} else {
				temp += c;
			}
		}
		return tokens;
	}
	
}

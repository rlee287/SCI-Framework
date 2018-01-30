package scilib.utilities;

import java.util.ArrayList;
import java.util.Scanner;

public class Tokenizer {
	
	public Tokenizer() {}
	
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

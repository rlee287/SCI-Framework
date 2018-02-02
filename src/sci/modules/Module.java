package sci.modules;

import java.util.ArrayList;

public interface Module {
	
	/**
	 * A getter for the command used to invoke this module.
	 * @return A String containing a word used to invoke this module.
	 */
	public String getInvoker();
	/**
	 * A getter for what this module will be referred to in the code.
	 * @return A String representation of this module to use when referencing this Module in the code.
	 */
	public String getName();
	/**
	 * Processes the given user input to execute commands and produce a String output.
	 * @param line String which represents the unaltered user input.
	 * @param tokens ArrayList of Strings for each 'word' in the user input, obtained by space-delimiting the input String.
	 * @return
	 */
	public String process(String line, ArrayList<String> tokens);
	/**
	 * The documentation for how this module should be used.
	 * @return String detailing the specifications of this Module. Invoked by the default included HelpModule.
	 */
	public String getHelpDoc(); // returns documentation on how a command should be used
}

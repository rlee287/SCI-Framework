package sci.modules;

import java.util.ArrayList;

/**
 * Interface of a Module object, which handles given user-inputted commands.
 * @author Squeakadoodle
 *
 */
public interface Module {
	
	/**
	 * Get the command used to invoke this module.
	 * @return String containing a word used to invoke this module.
	 */
	public String getInvoker();
	
	/**
	 * Get what this module will be referred to in the code.
	 * @return String representation of this module to use when referencing this Module in the code.
	 */
	public String getName();
	
	/**
	 * Get the documentation for how this module should be used.
	 * @return String detailing the specifications of this Module. Invoked by the default included HelpModule.
	 */
	public String getHelpDoc();
	
	/**
	 * Process the given user input to execute commands and produce a String output.
	 * @param line String which represents the unaltered user input.
	 * @param tokens ArrayList of Strings for each 'word' in the user input, obtained by space-delimiting the input String.
	 * @return String response to output to the user.
	 */
	public String process(String line, ArrayList<String> tokens);
}

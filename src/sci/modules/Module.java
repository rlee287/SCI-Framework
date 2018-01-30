package sci.modules;

import java.util.ArrayList;

public interface Module {
	
	public String getInvoker();	// return the command used to invoke this module
	public String getName(); 	// return the name of this module to be used for reference
	
	public String process(String line, ArrayList<String> tokens); 	// process the use of this module
	public String getHelpDoc(); // returns documentation on how a command should be used
}

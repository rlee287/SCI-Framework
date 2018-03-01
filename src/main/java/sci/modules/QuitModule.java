package sci.modules;

import java.util.ArrayList;

public class QuitModule implements Module {

	@Override
	public String getInvoker() {
		return "quit";
	}

	@Override
	public String getName() {
		return "Quit Module";
	}

	@Override
	public String getHelpDoc() {
		String response = "";
		response += "___[Quit] Usage___";
		response += "\nThe 'quit' command exits the program.";
		response += "\n------------------";
		return response;
	}

	@Override
	public String process(String line, ArrayList<String> tokens) {
		return null;
	}

}

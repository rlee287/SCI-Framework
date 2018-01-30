package sci.modules;

import java.util.ArrayList;

public class PingModule implements Module {
	// An example module to make sure that things are working as intended
	
	@Override
	public String getInvoker() {
		return "ping";
	}

	@Override
	public String getName() {
		return "Ping Module";
	}

	@Override
	public String process(String line, ArrayList<String> tokens) {
		return "pong";
	}

	@Override
	public String getHelpDoc() {
		String response = "";
		response += "___[Ping] Usage___";
		response += "\nThe 'ping' command returns \"pong\" when executed.";
		response += "\n------------------";
		return response;
	}

}

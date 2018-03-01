package sci.modules;

public class ExitModule extends QuitModule {

	@Override
	public String getInvoker() {
		return "exit";
	}

	@Override
	public String getName() {
		return "Exit Module";
	}
	
	@Override
	public String getHelpDoc() {
		String response = "";
		response += "___[Exit] Usage___";
		response += "\nThe 'exit' command exits the program.";
		response += "\n------------------";
		return response;
	}
}

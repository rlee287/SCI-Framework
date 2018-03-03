package sci.subprocesses;

import java.util.ArrayList;

import sci.SCI;
import sci.modules.Module;

/**
 * Default Subprocessor which waits for a yes/no answer.
 * @author Auxiliatrix
 *
 */
public class ConfirmSubprocessor implements Subprocessor {

	private String message;
	
	/**
	 * Creates a ConfirmSubprocessor.
	 */
	public ConfirmSubprocessor(String message) {
		this.message = message;
	}
	
	@Override
	public String iterate() {
		System.out.println(message);
		ArrayList<Module> exclude = new ArrayList<Module>();
		for( Module m : SCI.modules.values() ) {
			if( !m.getName().equals("Exit Module") ) {
				exclude.add(m);
			}
		}
		String response = SCI.subprocess(exclude);
		if( response == null ) {
			return null;
		} else if( response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n") || response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no") ) {
			return response;
		} else {
			System.out.println("Invalid response.");
			return iterate();
		}
	}
	
}

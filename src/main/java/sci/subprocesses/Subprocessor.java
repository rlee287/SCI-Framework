package sci.subprocesses;

/**
 * Interface of a subprocessor, which doesn't break until the user provides a valid response or an exit command.
 * @author Auxiliatrix
 *
 */
public interface Subprocessor {
	
	/**
	 * Recursively calls itself when an invalid response is given until a valid response is provided, or an exit command is given.
	 * @return String valid response provided by the user, or null if an exit command is given.
	 */
	public String iterate();
}

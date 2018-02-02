package scilib.checkers;

import scilib.objects.TeamData;

/**
 * Interface of a Checker object, which checks if a TeamData object meets its conditions.
 * @author Squeakadoodle
 *
 */
public interface Checker {
	
	/**
	 * Test if the given TeamData object meets this Checker's condition.
	 * @param td TeamData object to be checked against this Checker.
	 * @return Boolean result of whether this TeamData meets this Checker's condition.
	 */
	public boolean check(TeamData td);
	
	/**
	 * Test if this Checker is equal to another Checker.
	 * @param c Checker input to test against.
	 * @return Boolean result of whether the given Checker and this Checker are equal.
	 */
	public boolean equals(Checker c);
	
	/**
	 * Test if this Checker is equal to another Checker's String representation.
	 * @param s String representation of another Checker.
	 * @return Boolean result of whether the Checker the String is representing and this Checker are equal.
	 */
	public boolean equals(String s);
	
	/**
	 * Convert this Checker into a String.
	 * @return String representation of this Checker. Returns an empty String if this Checker is invalid.
	 */
	public String toString();

}

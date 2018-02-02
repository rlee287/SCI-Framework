package scilib.checkers;

import scilib.objects.TeamData;

public interface Checker {
	/**
	 * Tests if the given TeamData object meets this Checker's condition.
	 * @param td TeamData object to be checked against this Checker.
	 * @return Boolean result of whether this TeamData meets this Checker's condition.
	 */
	public boolean check(TeamData td);
	/**
	 * Tests if this Checker is equal to another Checker.
	 * @param c Checker input to test against.
	 * @return Boolean result of whether the given Checker and this Checker are equal.
	 */
	public boolean equals(Checker c);
	/**
	 * Tests if this Checker is equal to another Checker's String representation.
	 * @param s String representation of another Checker.
	 * @return Boolean result of whether the Checker the String is representing and this Checker are equal.
	 */
	public boolean equals(String s);
	/**
	 * Converts this Checker into a String.
	 * @return A String representation of this Checker. Returns an empty String if this Checker is invalid.
	 */
	public String toString();

}

package scilib.checkers;

import java.util.ArrayList;
import java.util.Collection;

import scilib.objects.TeamData;

/**
 * Checker extension which holds multiple checkers in a list.
 * @author Squeakadoodle
 *
 */
public class Filter implements Checker {

	private ArrayList<Checker> checkers;
	
	/**
	 * Construct a Filter object with an empty list of Checker objects.
	 */
	public Filter() {
		checkers = new ArrayList<Checker>();
	}
	
	@Override
	public boolean check(TeamData td) {
		for( Checker check : checkers ) {
			if( !check.check(td) ) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Checker c) {
		return equals(c.toString());
	}

	@Override
	public boolean equals(String s) {
		return this.toString().equals(s);
	}

	@Override
	public String toString() {
		String ret = "[Filter]";
		for( Checker check : checkers ) {
			ret += "\n" + check.toString();
		}
		return ret;
	}
	
	/**
	 * Get a list of Checker objects in this Filter.
	 * @return Shallow copy of the ArrayList of Checker objects in this Filter.
	 */
	public ArrayList<Checker> getCheckers() {
		return new ArrayList<Checker>(checkers);
	}
	
	/**
	 * Test if a Checker object is already a part of this Filter.
	 * @param c Checker object to be tested for.
	 * @return Boolean value representing if the Checker is already part of this Filter.
	 */
	private boolean contains(Checker c) {
		for( Checker check : checkers ) {
			if( check.equals(c) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Add a Collection of Checker objects to this Filter.
	 * @param checkList Collection of Checker objects to add.
	 * @return Boolean value indicating whether the list of Checker objects in this Filter was modified.
	 */
	public boolean addAll(Collection<Checker> checkList) {
		boolean success = false;
		for( Checker c : checkList ) {
			success |= add(c);
		}
		return success;
	}
	
	/**
	 * Add a Checker object to this Filter.
	 * @param c Checker object to add.
	 * @return Boolean value indicating whether the list of Checker objects in this Filter was modified.
	 */
	public boolean add(Checker c) {
		if( !contains(c) ) {
			checkers.add(c);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Remove a Checker object from this Filter.
	 * @param c Checker object to remove.
	 * @return Boolean value indicating whether the list of Checker objects in this Filter was modified.
	 */
	public boolean remove(Checker c) {
		return checkers.remove(c);
	}
	
	/**
	 * Remove all Checker objects from this Filter.
	 */
	public void reset() {
		checkers.clear();
	}
}

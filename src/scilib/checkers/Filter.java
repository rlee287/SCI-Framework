package scilib.checkers;

import java.util.ArrayList;
import java.util.Collection;

import scilib.objects.TeamData;

public class Filter implements Checker {

	private ArrayList<Checker> checkers;
	
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
	
	public ArrayList<Checker> getCheckers() {
		return new ArrayList<Checker>(checkers);
	}
	
	private boolean contains(Checker c) {
		for( Checker check : checkers ) {
			if( check.equals(c) ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean add(Collection<Checker> checkList) {
		boolean success = false;
		for( Checker c : checkList ) {
			success |= add(c);
		}
		return success;
	}
	
	public boolean add(Checker c) {
		if( !contains(c) ) {
			checkers.add(c);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean remove(Checker c) {
		return checkers.remove(c);
	}
	
	public void reset() {
		checkers.clear();
	}
}

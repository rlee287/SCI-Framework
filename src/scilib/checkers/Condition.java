package scilib.checkers;

import scilib.objects.TeamData;

public class Condition implements Checker {

	private enum Operator {
		NULL,
		LESS,
		LESS_OR_EQUAL,
		EQUAL,
		NOT_EQUAL,
		GREATER_OR_EQUAL,
		GREATER
	}
	
	public String dataType;
	public String testString;
	public Operator test;
	public double target;
	
	public Condition( String dataType, String operator, double target ) {
		this.dataType = dataType;
		if( operator.equals("<") ) {
			test = Operator.LESS;
		} else if( operator.equals("<=") ) {
			test = Operator.LESS_OR_EQUAL;
		} else if( operator.equals("=") ) {
			test = Operator.EQUAL;
		} else if( operator.equals("!") ) {
			test = Operator.NOT_EQUAL;
		} else if( operator.equals(">=") ) {
			test = Operator.GREATER_OR_EQUAL;
		} else if( operator.equals(">") ) {
			test = Operator.GREATER;
		} else {
			test = Operator.NULL;
		}
		this.target = target;
	}

	@Override
	public boolean check(TeamData td) {
		double value = td.get(dataType);
		if( test == Operator.LESS ) {
			return value < target;
		} else if( test == Operator.LESS_OR_EQUAL ) {
			return value <= target;
		} else if( test == Operator.EQUAL ) {
			return value == target;
		} else if( test == Operator.NOT_EQUAL ) {
			return value != target;
		} else if( test == Operator.GREATER_OR_EQUAL ) {
			return value >= target;
		} else if( test == Operator.GREATER ) {
			return value > target;
		} else {
			return true;
		}
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
		if( test == Operator.LESS ) {
			return dataType + " < " + target;
		} else if( test == Operator.LESS_OR_EQUAL ) {
			return dataType + " <= " + target;
		} else if( test == Operator.EQUAL ) {
			return dataType + " = " + target;
		} else if( test == Operator.NOT_EQUAL ) {
			return dataType + " ! " + target;
		} else if( test == Operator.GREATER_OR_EQUAL ) {
			return dataType + " >= " + target;
		} else if( test == Operator.GREATER ) {
			return dataType + " > " + target;
		} else {
			return "";
		}
	}
	
}

package scilib.checkers;

import java.util.ArrayList;

import sci.SCI;
import scilib.objects.TeamData;

/**
 * Checker which tests if a {@link TeamData} object meets a given condition.
 * @author Auxiliatrix
 *
 */
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
	public Operator test;
	public double target;
	
	/**
	 * Creates a Condition object with a given data parameter, comparison operator, and target value.
	 * @param dataType - String parameter of the data being tested
	 * @param operator - String representing the operation to test the TeamData object by
	 * @param target - Double target the TeamData object will be compared against.
	 */
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
		Double value = td.get(dataType);
		if( value == null ) {
			return false;
		}
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
	
	/**
	 * Given a String input, parses it and converts it into a Condition object if the input is valid.
	 * @param s - String input to create a Condition from
	 * @return A Condition object based on the input String, null if input is invalid
	 */
	public static Condition factory(String s) {
		ArrayList<String> tokens = SCI.t.parse(s);
		String type = "";
		String operator = "";
		double value = 0D;
		if( tokens.size() < 3 ) {
			return null;
		}
		if( !SCI.configuration.dataTypes.contains(tokens.get(0)) ) {
			return null;
		}
		else {
			type = tokens.get(0);
		}
		if( !(tokens.get(1).equals("<") || tokens.get(1).equals("<=") || tokens.get(1).equals("=") || tokens.get(1).equals("!") || tokens.get(1).equals(">") || tokens.get(1).equals(">=")) ) {
			return null;
		} else {
			operator = tokens.get(1);
		}
		try {
			value = Double.parseDouble(tokens.get(2));
		} catch(NumberFormatException n) {
			return null;
		}
		return new Condition(type, operator, value);
	}
	
	@Override
	public String toString() {
		if( dataType.isEmpty() ) {
			return "None";
		}
		else if( test == Operator.LESS ) {
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

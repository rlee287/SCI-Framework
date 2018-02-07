package scilib.objects;

/**
 * Parameters by which to sort a Group of TeamData objects.
 * @author Squeakadoodle
 *
 */
public class Sorter {
	
	public String dataType;
	public boolean increasing;
	
	/**
	 * Creates a Sorter object which weighs all TeamData objects equally.
	 */
	public Sorter() {
		this("");
	}
	
	/**
	 * Creates a Sorter object which weighs teams by a given DataType, in decreasing order.
	 * @param dataType - String representing the parameter by which to sort TeamData objects
	 */
	public Sorter( String dataType ) {
		this(dataType, false);
	}
	
	/**
	 * Creates a Sorter object which weighs teams by a given DataType, in either increasing or decreasing order.
	 * @param dataType - String representing the parameter by which to sort TeamData objects
	 * @param increasing - Boolean value indicating whether the data should be sorted in an increasing order
	 */
	public Sorter( String dataType, boolean increasing ) {
		this.dataType = dataType;
		this.increasing = increasing;
	}
	
	public String toString() {return "[Sort] " + dataType + "; " + ((increasing) ? "increasing" : "decreasing");}
}

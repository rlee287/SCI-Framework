package scilib.objects;

public class Sorter {
	
	public String dataType;
	public boolean increasing;
	
	public Sorter() {
		this("");
	}
	
	public Sorter( String dataType ) {
		this(dataType, false);
	}
	
	public Sorter( String dataType, boolean increasing ) {
		this.dataType = dataType;
		this.increasing = increasing;
	}
	
	public String toString() {return "[Sort] " + dataType + "; " + ((increasing) ? "increasing" : "decreasing");}
}

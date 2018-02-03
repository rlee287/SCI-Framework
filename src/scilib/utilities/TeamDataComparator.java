package scilib.utilities;

import java.util.Comparator;

import scilib.objects.Sorter;
import scilib.objects.TeamData;

/**
 * Comparator to compare two TeamData objects.
 * @author Squeakadoodle
 *
 */
public class TeamDataComparator implements Comparator<TeamData> {

	private Sorter sorter;
	
	/**
	 * Creates a TeamDataComparator given a Sorter object as a parameter.
	 * @param sorter - Sorter object to dictate what value to compare the TeamDataObjects by
	 */
	public TeamDataComparator(Sorter sorter) {
		this.sorter = sorter;
	}
	
	@Override
	public int compare(TeamData o1, TeamData o2) {
		String dataType = sorter.dataType;
		boolean increasing = sorter.increasing;
		if( !dataType.isEmpty() ) {
			return ((o1.get(dataType) > o2.get(dataType)) ^ increasing) ? 1 : -1;
		}
		return 0;
	}

}

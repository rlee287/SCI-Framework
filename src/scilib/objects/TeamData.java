package scilib.objects;

import java.util.HashMap;

/**
 * Object which stores data about a team's statistics.
 * @author Squeakadoodle
 *
 */
public class TeamData {
	
	private HashMap<String, Double> data;
	
	public String teamNumber;
	
	/**
	 * Construct a TeamData object with a given team name.
	 * @param teamNumber String identification of a team.
	 */
	public TeamData(String teamNumber) {
		data = new HashMap<String, Double>();

		this.teamNumber = teamNumber;
	}
	
	/**
	 * Get the data for a given statistic.
	 * @param dataType String parameter of the data being retrieved.
	 * @return Double value of the data for the given String parameter. If the parameter is invalid, return null.
	 */
	public Double get(String dataType) {
		return data.keySet().contains(dataType) ? data.get(dataType) : null;
	}
	
	/**
	 * Set data for a given statistic for this team.
	 * @param dataType String parameter of the data being added.
	 * @param value Double value of the data for the given String parameter.
	 */
	public void put(String dataType, double value) {
		data.put(dataType, value);
	}
	
}

package scilib.objects;

import java.util.HashMap;

public class TeamData {
	
	private HashMap<String, Double> data;
	
	public String teamNumber;
	
	public TeamData(String teamNumber) {
		data = new HashMap<String, Double>();

		this.teamNumber = teamNumber;
	}
	
	public Double get(String dataType) {
		return data.keySet().contains(dataType) ? data.get(dataType) : 0D;
	}
	
	public void put(String dataType, double value) {
		data.put(dataType, value);
	}
	
}

package scilib.objects;

import java.util.HashMap;

public class TeamData {
	
	public String teamNumber;
	private HashMap<String, Double> data;
	
	public TeamData(String teamNumber) {
		this.teamNumber = teamNumber;
		data = new HashMap<String, Double>();
	}
	
	public Double get(String dataType) {
		return data.keySet().contains(dataType) ? data.get(dataType) : 0D;
	}
	
	public void put(String dataType, double value) {
		data.put(dataType, value);
	}
	
}

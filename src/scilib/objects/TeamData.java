package scilib.objects;

import java.util.HashMap;

public class TeamData {
	
	public String teamNumber;
	public HashMap<String, Double> data;
	
	public TeamData(String teamNumber) {
		this.teamNumber = teamNumber;
		data = new HashMap<String, Double>();
	}
	
	public Double get(String dataType) {
		return data.get(dataType);
	}
	
}

package scilib.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import sci.SCI;
import scilib.objects.TeamData;

/**
 * Parser which takes data from a file to create TeamData objects.
 * @author Squeakadoodle
 *
 */
public class DataParser {
	
	private static FileConverter fc;
	
	/**
	 * Creates a DataParser object.
	 */
	public DataParser() {
		fc = new FileConverter();
	}
	
	/**
	 * Generates a list of TeamData objects from a text file.
	 * @param fileName - String name of the text file to retrieve information from
	 * @return all the TeamData objects generated from the given text file.
	 */
	public ArrayList<TeamData> process(String fileName) {
		ArrayList<TeamData> teams = new ArrayList<TeamData>();
		HashMap<String, ArrayList<Double>> data = new HashMap<String, ArrayList<Double>>();
		ArrayList<String> lines = fc.convert(fileName);
		
		for( String line : lines ) {
			if( fc.compress(line).startsWith("teamnumber") ) {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(line);
				sc.useDelimiter(",");
				while( sc.hasNext() ) {
					String next = sc.next();
					next = fc.compress(next);
					if( !(next.equals("") || next.equals("teamnumber")) ) {
						teams.add(new TeamData(next));
					}
				}
				break;
			}
		}
		
		for( String dataType : SCI.configuration.dataTypes ) {
			for( String line : lines ) {
				if( fc.compress(line).startsWith(dataType) ) {
					data.put(dataType, parse(dataType, line));
					break;
				}
			}
		}
		
		for( String key : data.keySet() ) {
			ArrayList<Double> variableData = data.get(key);
			for( int f=0; f<variableData.size(); f++ ) {
				TeamData team = teams.get(f);
				team.put(key, variableData.get(f));
			}
		}
		
		return teams;
	}
	
	private ArrayList<Double> parse(String dataType, String line) {
		ArrayList<Double> parseData = new ArrayList<Double>();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(line);
		sc.useDelimiter(",");
		while( sc.hasNext() ) {
			String next = sc.next();
			next = fc.compress(next);
			if( !(next.equals("") || next.equals(dataType)) ) {
				parseData.add(Double.parseDouble(next));
			}
		}
		return parseData;
	}
	
}

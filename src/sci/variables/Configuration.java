package sci.variables;

import java.util.ArrayList;
import java.util.Scanner;

import scilib.utilities.FileConverter;

/**
 * Configuration file which serves as a compiler-readable source of variables from the config.txt file.
 * @author Squeakadoodle
 *
 */
public class Configuration {
	
	private FileConverter fc;
	
	public String dataFile;
	public String decimalFormat;
	public ArrayList<String> dataTypes;
	
	/**
	 * Construct a Configuration object, and automatically parses data from the config.txt file.
	 */
	public Configuration() {
		fc = new FileConverter();
		
		dataFile = "";
		decimalFormat = "";
		dataTypes = new ArrayList<String>();
		
		update();
	}
	
	/**
	 * Parse data from the config.txt file, overwriting previous values.
	 */
	public void update() {
		ArrayList<String> lines = fc.convert(dataFile);
		for( String line : lines ) {
			if( line.startsWith("data_file: ") ) {
				line = line.substring(11);
				dataFile = line;
			} else if( line.startsWith("decimal_format: ") ) {
				line = line.substring(16);
				decimalFormat = line;
			} else if( line.startsWith("data_types: ") ) {
				line = line.substring(12);
				@SuppressWarnings("resource")
				Scanner sc_dt = new Scanner(line);
				sc_dt.useDelimiter(",");
				while( sc_dt.hasNext() ) {
					String next = fc.compress(sc_dt.next());
					if( dataTypes.contains(next) ) {
						System.out.println("WARNING: Duplicate data_type: \"" + next + "\"");
					} else if( !next.equals("") ) {
						dataTypes.add(next);
					}
				}
			}
		}
	}
	
}

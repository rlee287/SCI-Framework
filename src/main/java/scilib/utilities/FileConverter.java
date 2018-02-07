package scilib.utilities;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Converter to turn text files into an ArrayList of Strings.
 * @author Squeakadoodle
 *
 */
public class FileConverter {
	
	/**
	 * Creates a FileConverter object.
	 */
	public FileConverter() {}
	
	/**
	 * Converts a text file into an ArrayList of Strings.
	 * @param fileName - String name of the text file to retrieve information from
	 * @return list of Strings for each line in the text file.
	 */
	@SuppressWarnings("resource")
	public ArrayList<String> convert(String fileName) {
		ArrayList<String> lines = new ArrayList<String>();
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null)
            {
                lines.add(line);
            }
        }
        catch (FileNotFoundException E)
        {
            System.err.println("Error: Cannot find file "+fileName);
            return null;
        }
        catch (IOException E)
        {
            return null;
        }
        return lines;
	}
	
	/**
	 * Removes all spaces and '%' signs from a String.
	 * @param input - String to compress
	 * @return the compressed result.
	 */
	public String compress(String input) {
		return input.replace(" ", "").replace("%", "").toLowerCase();
	}
}

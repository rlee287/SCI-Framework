package scilib.utilities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Converter to turn text files into an ArrayList of Strings.
 * @author Squeakadoodle
 *
 */
public class FileConverter {
	
	/**
	 * Construct a FileConverter object.
	 */
	public FileConverter() {}
	
	/**
	 * Convert a text file into an ArrayList of Strings.
	 * @param fileName String name of the text file to retrieve information from.
	 * @return ArrayList of Strings for each line in the text file.
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
        catch (Exception E)
        {
        	return null;
        }
        return lines;
	}
	
	/**
	 * Remove all spaces and '%' signs from a String.
	 * @param input String to compress.
	 * @return String of the compressed result.
	 */
	public String compress(String input) {
		return input.replace(" ", "").replace("%", "").toLowerCase();
	}
}

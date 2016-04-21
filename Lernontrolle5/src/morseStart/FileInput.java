package morseStart;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileInput {
	
	/**
	 * Textdatei in Stringarray einlesen
	 * 
	 * @param fileName  - Name der Datei.
	 * @return das gefüllte Stringarray.
	 */

	public static String[] readTextFile(String fileName) {
		String 			res[] = null;
		Vector<String>	vec = new Vector<String>();
		
		try {
			BufferedReader		br = new BufferedReader(new FileReader(fileName));
		
			String	line;
			while ((line = br.readLine())!=null) {
				vec.add(line);
			}
			res = (String[]) vec.toArray(new String[0]);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}


}

package projectgui;
import java.io.*;

/**
 * <h1>Process Class for char to char match</h1>
 * This class process the source code for char to char match
 * @author Mursalin
 * @version 1.0.0
 */
public class ProcessCharToChar {
    String ret;

    /**
     * reads the source code and returns a string
     * @param path path to source code
     * @return String
     */
    public String CharToChar(String path){
        ret="";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String CurrentLine;

            while ((CurrentLine = br.readLine()) != null) {
                    ret+=CurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}

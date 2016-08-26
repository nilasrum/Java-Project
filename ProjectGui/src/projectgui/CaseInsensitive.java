package projectgui;

import java.io.*;
import java.util.StringTokenizer;

/**
 * This class reads code from source folder and makes it ready for matching
 * @author Mursalin
 * @version 1.0.0
 */
public class CaseInsensitive {
    String ret;

    /**
     * Transform all the character of a source code to lower case
     * and generate a string  
     * @param path path to source code
     * @return String
     */
    public String tolowerall(String path){
        ret="";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String CurrentLine;

            while ((CurrentLine = br.readLine()) != null) {
                    ret+=CurrentLine.toLowerCase();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return ret;
    }
}

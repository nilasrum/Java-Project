package projectgui;

import java.io.*;
import java.util.StringTokenizer;

public class CaseInsensitive {
    String ret;
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

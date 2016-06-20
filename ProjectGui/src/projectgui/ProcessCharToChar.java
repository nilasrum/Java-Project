package projectgui;
import java.io.*;

public class ProcessCharToChar {
    String ret;
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

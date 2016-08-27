package projectgui;
import java.io.*;

/**
 * <h1>Process string for ignore-space match</h1>
 * This class process a string and makes it ready for MatchIgnorespace 
 * @author Mursalin
 * @version 1.0.0
 */
public class ProcessIgnoreSpace {
    String ret,temp;

    /**
     * reads the source code and generates a String ignoring all the space
     * @param path path to source code
     * @return String
     */
    public String IgnoreSpace(String path){
        ret="";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String CurrentLine;
            while ((CurrentLine = br.readLine()) != null) {
                temp = "";
                for(int i=0;i<CurrentLine.length();i++){
                    if(CurrentLine.charAt(i)!=' '){
                        temp+=CurrentLine.charAt(i);
                    }
                }
                ret+=temp.toLowerCase();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}

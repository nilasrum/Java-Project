package projectgui;
import java.io.*;

public class ProcessIgnoreSpace {
    String ret;
    public String IgnoreSpace(String path){
        ret="";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String CurrentLine;

            while ((CurrentLine = br.readLine()) != null) {
                
                for(int i=0;i<CurrentLine.length();i++){
                    if(CurrentLine.charAt(i)!=' '){
                        ret+=CurrentLine.charAt(i);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}

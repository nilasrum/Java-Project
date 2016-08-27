package projectgui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <h1>Class for hash-match process</h1>
 * This class makes the source code ready for Hash-match
 * @author Mursalin
 * @version 1.0.0
 */
public class HashMatchProcess {

    final String[] badLineBeginnings = {"#include", "#define", "//", "using", "template", "import", "package", "/*"};

    /**
     * Finds if a string starts with certain keyword
     * @param s String 
     * @return boolean
     */
    public boolean startsBadly(String s) {
        for (String bad : badLineBeginnings) {
            if (s.startsWith(bad)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads the source code and process it for hash-matching
     * @param path path to the source code
     * @return String
     */
    public String ProcessHashMatch(String path) {

        String ret = "",temp;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String CurrentLine;

            while ((CurrentLine = br.readLine()) != null) {
                if (startsBadly(CurrentLine)==true || CurrentLine.endsWith("*/")) {
                    //contain keyword,comment etc
                }
                else{
                    temp = "";
                    for(int i=0;i<CurrentLine.length();i++)
                    {
                        if(CurrentLine.charAt(i)!=' '){
                            temp+=CurrentLine.charAt(i);
                        }
                    }
                    ret+=temp.toLowerCase();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}

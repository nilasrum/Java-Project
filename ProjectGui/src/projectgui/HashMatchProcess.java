package projectgui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashMatchProcess {

    final String[] badLineBeginnings = {"#include", "#define", "//", "using", "template", "import", "package", "/*"};

    public boolean startsBadly(String s) {
        for (String bad : badLineBeginnings) {
            if (s.startsWith(bad)) {
                return true;
            }
        }
        return false;
    }

    public String ProcessHashMatch(String path) {

        String ret = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String CurrentLine;

            while ((CurrentLine = br.readLine()) != null) {
                if (startsBadly(CurrentLine)==true || CurrentLine.endsWith("*/")) {
                    //contain keyword,comment etc
                }
                else{
                    ret+=CurrentLine.toLowerCase();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}

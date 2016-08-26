package projectgui;

import java.io.*;
import java.text.DecimalFormat;
import javafx.util.converter.DoubleStringConverter;

/**
 * <h1>Char to Char Match</h1>
 * This class match two string by char to char
 * @author Mursalin
 * @version 1.0.0
 */
public class MatchCharToChar {

    /**
     * finds match percentage between two string matching char to char
     * @param one string one
     * @param two string two
     * @return double
     */
    public double FindMatch(String one,String two){
        int total = Math.max(one.length(),two.length());
        int lim = Math.min(one.length(),two.length());
        int cnt = 0;
        double ret=0.0;
        for(int i=0;i<lim;i++){
            if(one.charAt(i)==two.charAt(i))cnt++;
        }
        ret = (cnt*1.0*100)/(total*1.0);
        DecimalFormat df = new DecimalFormat("#.00");
        DoubleStringConverter sdc = new DoubleStringConverter();
        ret=sdc.fromString(df.format(ret));
        return ret;
    }
}

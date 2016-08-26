package projectgui;

import java.io.*;
import java.text.DecimalFormat;
import javafx.util.converter.DoubleStringConverter;

/**
 * <h1>Match by ignoring space</h1>
 * This class match two string char to char ignoring the space between them
 * @author Mursalin
 * @version 1.0.0
 */
public class MatchIgnoreSpace {

    /**
     * return match percentage between two string
     * @param one string one
     * @param two string two
     * @return double
     */
    public double FindIgnoreSpaceMatch(String one,String two){
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

package projectgui;

import java.io.*;
import java.text.DecimalFormat;
import javafx.util.converter.DoubleStringConverter;

public class MatchCharToChar {
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

package projectgui;

import java.util.ArrayList;


public class AllAssignment {
    public ArrayList AssiNames(int n){
        ArrayList<String> ret = new ArrayList();
        char ch = 65;
        String str;
        for(int i=0;i<n;i++){
            ch=(char)(65+i);
            str=Character.toString(ch);
            ret.add(str);
        }
        return ret;
    }
}

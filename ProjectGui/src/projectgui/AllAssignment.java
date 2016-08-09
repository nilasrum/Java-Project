package projectgui;

import java.util.ArrayList;


public class AllAssignment {
    public ArrayList AssiNames(int n){
        ArrayList<String> ret = new ArrayList();
        String str = "";
        for(int i=1;i<=n;i++){
            str=Integer.toString(i);
            ret.add(str);
        }
        return ret;
    }
}

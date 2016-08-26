package projectgui;

import java.util.ArrayList;

/**
 *<h1>Assignment List</h1>
 * Creates a list with assignment names
 * @author Mursalin
 * @version 1.0.0
 */
public class AllAssignment {

    /**
     * Creates a list of assignment name provided with assignment number 
     * @param n number of assignment
     * @return ArrayList
     */
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

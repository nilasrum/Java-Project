package projectgui;

import java.util.ArrayList;

/**
 * <h1>Index of an assignment</h1>
 * @author Mursalin
 * @version 1.0.0
 */
public class GetAssignmentName {
    
    /**
     * returns the index of a name
     * @param list ArrayList of assignment names
     * @param name search name
     * @return integer
     */
    public int GetNameIndex(ArrayList list,String name){
        String listname;
        for(int i=0;i<list.size();i++){
            listname=(String) list.get(i);
            if(listname.startsWith(name))return i;
        }
        return 0;
    }
    
}

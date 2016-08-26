package projectgui;

import java.util.ArrayList;

/**
 * <h1>Check for missing assignment</h1>
 * @author Mursalin
 * @version 1.0.0
 */
public class CheckMissingAssi {
    
    /**
     * Generates and returns a String from filename without extension
     * @param filename name of the file with extension
     * @return String
     */
    public String WithoutExtension(String filename){
        String ret="";
        ret = filename.substring(0, filename.lastIndexOf('.'));
        
        return ret;
    }
    
    /**
     * Checks if a name is missing from a List
     * @param list ArrayList of names
     * @param name name to check
     * @return true if found else false
     */
    public boolean isMissing(ArrayList list,String name){
        String str;
        for(int i=0;i<list.size();i++){
            
            str = (String) list.get(i);
            str = WithoutExtension(str);
                    
            if(str.equals(name))return false;
        }
        return true;
    }
    
}

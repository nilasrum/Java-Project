package projectgui;

import java.util.ArrayList;


public class CheckMissingAssi {
    
    public String WithoutExtension(String filename){
        String ret="";
        ret = filename.substring(0, filename.lastIndexOf('.'));
        
        return ret;
    }
    
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

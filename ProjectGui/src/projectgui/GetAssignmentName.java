package projectgui;

import java.util.ArrayList;


public class GetAssignmentName {
    
    int GetNameIndex(ArrayList list,String name){
        String listname;
        for(int i=0;i<list.size();i++){
            listname=(String) list.get(i);
            if(listname.startsWith(name))return i;
        }
        return 0;
    }
    
}

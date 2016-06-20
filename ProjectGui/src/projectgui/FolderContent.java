package projectgui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class FolderContent {
    public static ArrayList InFolder(String path){
        File f = new File(path);
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
        return names;
    }
}

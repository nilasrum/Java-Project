package projectgui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h1>Content of a folder</h1>
 * @author Mursalin
 */
public class FolderContent {

    /**
     * returns a list of names of the content of a folder
     * @param path path to folder
     * @return ArrayList
     */
    public static ArrayList InFolder(String path){
        File f = new File(path);
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
        return names;
    }
}

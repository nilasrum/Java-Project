package projectgui;

import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
* <h1>Controller For Main Interface</h1>
* This class Controls the events of main interface
* @author  Mursalin
* @see Initializable
* @version 1.0.0
*/

public class FXMLDocumentController implements Initializable {
    
    private Stage stage;
    private boolean flag = false;
    private Main mainLogic;
    private String path;
    ObservableList<String> assiList = FXCollections.observableArrayList("1","2","3","4","5","6","7",
            "8","9","10","11","12","13","14","15");
    
    @FXML
    private TextField pathbox;
    @FXML
    private Button processButton;
    @FXML
    private Button BrowseButton;
    @FXML
    private Label Assiglabel;
    @FXML
    private ComboBox<String> Assinum;
    @FXML
    ImageView logo;
    @FXML
    private Label Assinumlabel;
    
    /**
     * This method handle browse button click action
     * provides a directory chooser for user to select
     * the assignment containing folder
     */
    @FXML
    private void DirectoryPath(){
    
        DirectoryChooser  dc = new DirectoryChooser();
        File file = dc.showDialog(stage);
        if(file!=null){
            flag=true;
            path = file.getAbsolutePath();
            pathbox.setText(path);
            mainLogic = new Main();
            mainLogic.pathToInputFolder = path;
        }
    }
    
    
    /**
     * This method handle process button click action
     * Takes user specified folder path and assignment 
     * number and pass it to main class
     */
    @FXML
    private void ProcessData() {
        
        if(flag==true && Assinum.getValue() != null) {
            mainLogic.N = Integer.parseInt(Assinum.getValue());
            pathbox.setText("");
            flag=false;
            mainLogic.run();
        }
    }
    
    
    /**
     * This method initialize the main interface
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image vlogo = new Image("res/logo.png");
        logo.setImage(vlogo);
        Assinum.setItems(assiList);
        Assiglabel.getStyleClass().add("label-message");
        Assinumlabel.getStyleClass().add("label-message");
    }    
    
}

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


public class FXMLDocumentController implements Initializable {
    
    private Stage stage;
    private boolean flag = false;
    private Main mainLogic;
    public String path="";
    
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
    ObservableList<String> assiList = FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
        
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
    
    @FXML
    private void ProcessData()throws Exception{
        
        if(flag==true && Assinum.getValue() != null) {
            mainLogic.N = Integer.parseInt(Assinum.getValue());
            mainLogic.run();
            AlertFXMLController alert = new AlertFXMLController();
            alert.getNum(Integer.parseInt(Assinum.getValue()));
            alert.TestScene();
            pathbox.setText("");
            flag=false;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image vlogo = new Image("projectgui/logo.png");
        logo.setImage(vlogo);
        Assinum.setItems(assiList);
    }    
    
}

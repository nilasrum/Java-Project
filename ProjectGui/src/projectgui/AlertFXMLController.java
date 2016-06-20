package projectgui;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.setUserAgentStylesheet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertFXMLController implements Initializable {

    private static Stage window;
    public static int num;
    
    @FXML
    Button backButton;
    @FXML
    Button showButton;
    @FXML
    Label completed;
    
    public void TestScene()throws Exception{
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("AlertFXML.fxml"));
        Scene scene = new Scene(root);
        String css = ProjectGui.class.getResource("DarkTheme.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }
    
    public void getNum(int n){
        num=n;
    }
    
    @FXML
    private void CloseWindow(){
        window.close();
    }
    
    @FXML
    private void ResultGui()throws Exception{
        window.close();
        ResultGuiFXMLController rgc = new ResultGuiFXMLController();
        rgc.GetAssiNum(num);
        rgc.ResScene();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

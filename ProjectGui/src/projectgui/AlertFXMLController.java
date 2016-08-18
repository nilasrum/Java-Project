package projectgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class AlertFXMLController implements Initializable {

    private static Stage window;
    private static int num;
    
    @FXML
    Button backButton;
    @FXML
    Button showButton;
    @FXML
    Label msg1;
    @FXML
    Label msg2;
    
    public void TestScene(Stage stage)throws Exception{
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlpack/AlertFXML.fxml"));
        Scene scene = new Scene(root);
        String css = ProjectGui.class.getResource("/res/DarkTheme.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.setResizable(false);
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
        msg1.getStyleClass().add("label-message");
        msg2.getStyleClass().add("label-message");
    }    
    
}

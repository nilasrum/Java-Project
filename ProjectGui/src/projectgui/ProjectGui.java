package projectgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Mursalin
 */
public class ProjectGui extends Application {
    
    public static Stage window;
    Scene scene2;
    
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setResizable(false);
        window.getIcons().add(new Image("projectgui/frameicon.png"));
        Parent root = FXMLLoader.load(getClass().getResource("MainGuiFXML.fxml"));
        Scene scene = new Scene(root);
        String css = ProjectGui.class.getResource("DarkTheme.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

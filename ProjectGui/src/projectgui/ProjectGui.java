package projectgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h1>Main Entry point to App</h1>
 * This class loads the starting Interface of the app
 * @author Mursalin
 * @version 1.0.0
 */
public class ProjectGui extends Application {
    
    private Stage window;
    
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setResizable(false);
        window.getIcons().add(new Image("res/frameicon.png"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlpack/MainGuiFXML.fxml"));
        Scene scene = new Scene(root);
        String css = ProjectGui.class.getResource("/res/DarkTheme.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.show();
    }
    
    /**
     * This is the main method which makes use of start method.
     * @param args Unused.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

package projectgui;

import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProgressWindow {

    public Stage window;
    public ProgressBar probar;
    
    public void LoadProgressBar(Task task) {
        window = new Stage();
        probar = new ProgressBar();
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.getIcons().add(new Image("projectgui/frameicon.png"));
        Label label = new Label("Processing...Please wait");
        label.getStyleClass().add("label-message");
        probar.setPrefWidth(250);
        probar.setProgress(0.0);
        VBox root = new VBox();
        root.getChildren().addAll(label, probar);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        Scene scene = new Scene(root, 315, 145);
        String css = ProjectGui.class.getResource("DarkTheme.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.show();
        probar.progressProperty().bind(task.progressProperty());
    }
}

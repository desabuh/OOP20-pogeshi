package views;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class DeckCreationViewImpl extends Application implements DeckCreationView {

    private static final int SCENE_WIDTH = 1920;
    private static final int SCENE_HEIGHT = 1080;

    public static void main(final String[] args) throws java.io.IOException {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts" + File.separator + "deck_view.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        // Stage configuration
        stage.setTitle("JavaFX - Complete Example");
        stage.setScene(scene);
        stage.show();
    }
}

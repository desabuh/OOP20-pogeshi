package application;

import com.google.inject.Guice;
import com.google.inject.Injector;

import guicemodule.ControllerModule;
import guicemodules.FXMLLoaderModule;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 300;

    @Override
    public void start(final Stage stage) throws Exception {
        // final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
        // final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        Injector injector = Guice.createInjector(new ControllerModule());

        SceneManager.provideControllerFactory(Injector::getInstance);

        Scene mainScene = SceneManager.of(LAYOUT.ACCOUNT).getScene();

        // Stage configuration
        stage.setTitle("Pogeshi");
        stage.setScene(mainScene);
        stage.show();
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}

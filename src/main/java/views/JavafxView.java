package views;

import javafx.scene.Scene;
import javafx.stage.Stage;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

/**
 * 
 * View implementation for JavaFX.
 *
 */
public final class JavafxView implements View {
    /**
     * width of the view.
     */
    public static final int WIDTH = 100;
    /**
     * height of the view.
     */
    public static final int HEIGHT = 100;
    /**
     * title to be displayed on the view.
     */
    public static final String TITLE = "Pogeshi";

    private Scene actualScene;
    private final Stage stage;


    public JavafxView(final Stage stage) {
        this.stage = stage;
    }


    @Override
    public void loadScene(final LAYOUT layout) {
        this.actualScene = SceneManager.of(layout).getScene();
        this.stage.setTitle(TITLE);
        stage.setScene(this.actualScene);
        stage.show();
    }

}

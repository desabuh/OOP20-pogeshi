package views;



import models.GameMap.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.render.Render;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

/**
 * 
 * View implementation for JavaFX.
 *
 */
public abstract class JavafxView implements View {
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

    public JavafxView(final Stage stage, final LAYOUT initialLayout) {
        this.stage = stage;
    }

    public final Scene getScene() {
        return this.actualScene;
    }


    @Override
    public final void setScene(final Scene scene) {
        this.actualScene = scene;
    }

    @Override
    public final void loadScene(final LAYOUT layout) {
        this.actualScene = SceneManager.of(layout).getScene();
        this.stage.setTitle(TITLE);
        stage.setScene(this.actualScene);
        stage.show();
    }

    @Override
    public abstract void updateEntity(Render render, Point2D x, Point2D y);

}

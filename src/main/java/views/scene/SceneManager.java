package views.scene;






import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;
import views.scene.layout.LAYOUT;

/**
 *   class to provide basic loader functionality and scene wrapping.
 */
public final class SceneManager {

    /**
     * static mapper to return a SceneManager to work on the specific layout.
     */
    public static final Map<LAYOUT, SceneManager> MANAGERS = new HashMap<>();

    /**
     * static factory rule to provide a controller for the current Loader.
     */
    public static Callback<Class<? extends Controller>, Controller> FACTORY;

    /**
     * actual Scene instance.
     */
    private final Scene providedScene;

    /**
     * loader used.
     */
    private final FXMLLoader loader;

    /**
     * static factory to provide a SceneManager instance.
     * @param layout enum related to layout to load
     * @return SceneManager instance
     */
    public static SceneManager of(final LAYOUT layout) {
        return MANAGERS.computeIfAbsent(layout, ignored -> new SceneManager(ignored));
    }

    /**
     * private constructor to create a new SceneManager.
     * @param layout enum related to layout to load
     */
    private SceneManager(final LAYOUT layout) {
        this.loader = new FXMLLoader();
        this.loader.setControllerFactory(FACTORY);
        Parent parent = this.loader.load(ClassLoader.getSystemResourceAsStream(layout.getPathString()));
        this.providedScene =  new Scene(parent);
    }

    /**
     * retrieve scene instance.
     * @return providedScene
     */
    public Scene getScene() {
        return this.providedScene;
    }

    /**
     * retrieve loader instance.
     * @return loader
     */
    public FXMLLoader getLoader() {
        return this.loader;
    }

    /**
     * static method used to provide an alternative factory to the standard one for providing controllers.
     * @param controllerFactory alternative controller factory to provide controllers
     */
    public static void provideControllerFactory(final Callback<Class<? extends Controller>, Controller> controllerFactory) {
        FACTORY = controllerFactory;
    }

    /*1)PS provide a builder to create the scene, 2)wrap scene object*/
    /**
     * method used to switch to another scene(*working*).
     * @throws IOException if an error occured when loading the layout
     * @param layout layout enum provided
     * @return scene to be loaded
     */
    public Scene loadScene(final LAYOUT layout) throws IOException {
        final Parent sceneRoot = FXMLLoader.load(layout);
        return new Scene(sceneRoot, 100, 100);
    }


}

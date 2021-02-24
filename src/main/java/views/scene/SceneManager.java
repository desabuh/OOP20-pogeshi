package views.scene;






import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public static final Map<LAYOUT, SceneManager> MANAGERS = new ConcurrentHashMap<>();

    /**
     * static factory rule to provide a controller for the current Loader.
     */
    public static Callback<Class<?>, Object> FACTORY;

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
        Parent parent = null;
        try {
            parent = this.loader.load(ClassLoader.getSystemResourceAsStream(layout.getPathString()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    public static void provideControllerFactory(final Callback<Class<?>, Object> controllerFactory) {
        FACTORY = controllerFactory;
    }

}

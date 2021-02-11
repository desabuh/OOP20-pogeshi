package views.drawer;

import javafx.scene.Node;
import views.render.Render;

/**
 * 
 * interface to perform drawing operation on view.
 * @param <T> component where drawing is performed(must be a javafx Scene node)
 */
public interface Drawer<T extends Node> {

    /**
     * 
     * @param render to draw on {@link <T>} component.
     */
    void draw(Render render);
}

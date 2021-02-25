package views.drawer;

import models.GameMap.Point2D;
import javafx.scene.Node;
import views.render.Render;

/**
 * 
 * interface to perform drawing operation on view.
 * @param <T> component where drawing is performed(must be a javafx Scene node)
 */
public interface Drawer<T extends Node> {

    /**
     * function to draw a render in a specific destination.
     * @param render to draw on component
     * @param source upper left bound
     * @param destination upper left bound
     */
    void draw(Render render, Point2D source, Point2D destination);

}

package views;

import javafx.scene.Scene;
import models.GameMap.Point2D;
import views.render.Render;
import views.scene.layout.LAYOUT;

/**
 * 
 * interface for View.
 *
 */
public interface View {

    /**
     * load a Scene inside this view.
     * @param layout layout to load for
     */
    void loadScene(LAYOUT layout);

    /**
     * a method provided to display or update an entity by its render.
     * @param render of the entity to display
     * @param src upper left bound dest
     * @param dest upper left bound src
     */
    void updateEntity(Render render, Point2D src, Point2D dest);

    /**
     * method to provide the wrapped view the actual Scene.
     * @param scene to provide for the view
     */
    void setScene(Scene scene);

}

package views;

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
     */
    void updateEntity(Render render);

}

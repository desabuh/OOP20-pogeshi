package views.drawer;

import views.render.Render;

/**
 * 
 * interface to perform drawing operation on view.
 * @param <T> component where drawing is performed
 */
public interface Drawer<T> {

    /**
     * 
     * @param render to draw on {@link <T>} component.
     */
    void draw(Render render);
}

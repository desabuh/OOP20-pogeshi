package views;

import views.scene.layout.LAYOUT;

/**
 * 
 * interface for View.
 *
 */
public interface View {

    /**
     * load a Scene inside this view.
     * @param layout layout to load as scene
     */
    void loadScene(LAYOUT layout);

}

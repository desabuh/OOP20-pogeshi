package views.render;

/**
 * 
 *interface to define all object that could be rendered.
 *
 */
public interface Renderable {

    /**
     * accept a render logic for this object.
     * @param render logic to apply for the implementing object
     */
    void accept(Renderer render);
}

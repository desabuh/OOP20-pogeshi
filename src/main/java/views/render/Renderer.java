package views.render;

/**
 * 
 * interface to provide a separate render mechanism from the object they rappresent.
 *
 */
public interface Renderer {

    /**
     * performs Player render action.
     * @param player to render
     */
    void render(Player player);

    /**
     * performs Enemy render action.
     * @param enemy to render
     */
    void render(Enemy enemy);

}

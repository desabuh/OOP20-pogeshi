package views.render;

/**
 * 
 * interface to provide a separate render mechanism from the object they rappresent.
 *
 */
public interface RenderFactory {

    /**
     * performs Player render action.
     * @param player to render
     * @return a render for this player
     */
    Render render(Player player);

    /**
     * performs Enemy render action.
     * @param enemy to render
     * @return a render for this enemy
     */
    Render render(Enemy enemy);

    /**
     * performs a Background render action.
     * (the background is loaded based on a spcific view)
     * @return a render for this 
     */
    Render renderBackGround();

}

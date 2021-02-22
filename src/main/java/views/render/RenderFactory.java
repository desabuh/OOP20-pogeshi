package views.render;


/**
 * 
 * interface to provide a separate render mechanism from the object they rappresent.
 *
 */
public interface RenderFactory {

    /**
     * performs Player render action.
     * @return a render for this player
     */
    Render renderPlayer();

    /**
     * performs Enemy render action.
     * @return a render for this enemy
     */
    Render renderEnemy();

    /**
     * performs Enemy boss render action.
     * @return a render for the enemy boss
     */
    Render renderEnemyBoss();

}

package models.GameMap;

import java.util.List;

import java.util.Optional;

import controllers.MOVEMENT;
import models.Character.EnemyImp;
import models.Character.Player;


public interface WorldMap {


    /**
     * set player data.
     * @param player new player data
     */
    void setPlayer(Player player);

    /**
     * retrieve player.
     * @return player instance
     */
    Player getPlayer();

    /**
     * Interact with nearby enemies.
     * @return an empty Optional if no adjacent enemies are founded, otherwise the first occurence of nearby enemy
     */
    Optional<EnemyImp> playerInteract();

    /**
     * retrieve boss enemy.
     * @return an empty Optional if all the enemies are not defeated, otherwise enemy boss instance
     */
    Optional<EnemyImp> getBoss();

    /**
     * remove enemy when defeated.
     * @param enemy to be removed
     */
    void removeEnemy(EnemyImp enemy);

    /**
     * retrieve all enemies.
     * @return enemy list
     */
    List<EnemyImp> getEnemies();

    /**
     * 
     * @param direction {@link MOVEMENT} as strategy for movement.
     * @return a {@link Point2D} if movement is valid, otherwise empty Optional
     */
    Optional<Point2D> updatePlayerPosition(MOVEMENT direction);
}

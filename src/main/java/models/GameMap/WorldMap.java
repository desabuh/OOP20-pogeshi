package models.GameMap;

import java.util.List;
import java.util.Optional;

public interface WorldMap {

    String getPlayer();

    Optional<String> playerInteract();

    String getBoss();

    List<String> getEnemies();

    void updatePlayerPosition(String direction);
}

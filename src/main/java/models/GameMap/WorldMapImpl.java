package models.GameMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import javafx.util.Pair;

public class WorldMapImpl implements WorldMap {

    private BiMap<String, Pair<Integer, Integer>> gameEntities = ImmutableBiMap.of(
                                                                    "Player", new Pair<>(1, 1),
                                                                    "Enemy", new Pair<>(5, 5),
                                                                    "Boss", new Pair<>(3, 6));

    @Override
    public final String getPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Optional<String> playerInteract() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final String getBoss() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final List<String> getEnemies() {
        return this.gameEntities.keySet().stream()
                .filter(x -> x.equals("Enemy"))
                .collect(Collectors.toList());
    }

    @Override
    public void updatePlayerPosition(final String direction) {
        

    }

}

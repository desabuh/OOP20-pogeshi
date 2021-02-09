package models.GameMap.Terrain;

import java.util.Optional;

public interface Terrain<T> {

    Optional<T> retrieveElement(int row, int column);

    T removeElement(int row, int column);



}

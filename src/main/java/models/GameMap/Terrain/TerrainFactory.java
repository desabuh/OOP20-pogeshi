package models.GameMap.Terrain;

public interface TerrainFactory {

    <X> Terrain<X> createTerrain();
}

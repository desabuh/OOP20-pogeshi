package worldMapTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import controllers.MOVEMENT;
import controllers.WorldMapController;
import guicemodule.ComunicationModule;
import guicemodule.WorldMapModule;
import models.Character.EnemyImp;
import models.GameMap.Point2D;
import models.GameMap.Point2DImp;
import models.GameMap.WorldMap;
import models.GameMap.WorldMapImpl;


public final class TestWorldMap {

    private WorldMap worldMap;

    @BeforeEach
    public void setUp() {
        Injector injector =  Guice.createInjector(new WorldMapModule(null), new ComunicationModule());
        this.worldMap = injector.getInstance(WorldMapImpl.class); 
    }

    @Test
    public void checkInteraction() {
        this.worldMap.getPlayer().setPosition(Point2DImp.setPoint(0, (WorldMapImpl.NUM_COL / 2) - 1));
        Optional<EnemyImp> enemy =  this.worldMap.playerInteract();
        assertNotEquals(enemy, Optional.empty()); 
    }

    @Test
    public void checkMovement() {
        Optional<Point2D> shouldBeEmpty =  this.worldMap.updatePlayerPosition(MOVEMENT.LEFT);
        assertEquals(Optional.empty(), shouldBeEmpty);
        this.worldMap.updatePlayerPosition(MOVEMENT.DOWN);
        this.worldMap.updatePlayerPosition(MOVEMENT.RIGTH);
        assertEquals(this.worldMap.getPlayer().getPosition(), Point2DImp.setPoint(1, 1));
    }

    @Test
    public void checkOutOfBoundsPosition() {
        this.worldMap.updatePlayerPosition(MOVEMENT.RIGTH);
        this.worldMap.updatePlayerPosition(MOVEMENT.DOWN);
        this.worldMap.updatePlayerPosition(MOVEMENT.RIGTH);
        this.worldMap.updatePlayerPosition(MOVEMENT.UP);
        assertEquals(Point2DImp.setPoint(0, 2), this.worldMap.getPlayer().getPosition());
        assertEquals(Optional.empty(), this.worldMap.updatePlayerPosition(MOVEMENT.UP));
    }

    @Test
    public void testToVisualDestination() {
        Optional<Point2D> newPos =  this.worldMap.updatePlayerPosition(MOVEMENT.DOWN);
        assertNotEquals(Optional.empty(), newPos);
        Point2D visualDest = WorldMapController.DISPLAY_FUN.applyTransform(newPos.get());
        assertEquals(Point2DImp.setPoint(WorldMapController.DELTA, 0), visualDest);
    }



}

package controllers;

import com.google.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.GameMap.WorldMap;
import views.View;
import views.render.Render;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

public final class WorldMapController implements Controller {

    private View worldView;
    private WorldMap worldMap;

    @Inject
    public WorldMapController(final WorldMap worldMap, final View worldView) {
        this.worldMap = worldMap;
        this.worldView = worldView;
    }

    @FXML 
    private void initialize() {
        /**
         * the scene is setted for the view only when javaFX MainThread is loaded
         */
        Platform.runLater(() ->  this.worldView.setScene(SceneManager.of(LAYOUT.WORLDMAP).getScene()));
    }


    @FXML
    void drawPlayer() {
        this.worldView.updateEntity(new Render(100, 100, 1), new Point2D(500,500), new Point2D(500,500));
    }

    @FXML
    void movePlayer(final KeyEvent keyEvent) {
        /*temporany for testing, add enum*/
        if (keyEvent.getCode().equals(KeyCode.W)) {
            this.worldView.updateEntity(new Render(100, 100, 1), new Point2D(500,500), new Point2D(500,400));
        }
        if (keyEvent.getCode().equals(KeyCode.S)) {
            this.worldView.updateEntity(new Render(100, 100, 1), new Point2D(500,500), new Point2D(500,600));
        }
        if (keyEvent.getCode().equals(KeyCode.E)) {
            this.worldView.updateEntity(new Render(100, 100, 1), new Point2D(500,500), new Point2D(600,500));
        }
        if (keyEvent.getCode().equals(KeyCode.S)) {
            this.worldView.updateEntity(new Render(100, 100, 1), new Point2D(500,500), new Point2D(400,500));
        }


    }
}

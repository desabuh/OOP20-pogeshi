package controllers;



import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
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
        this.worldView.updateEntity(new Render(100, 100, 1));
    }
}

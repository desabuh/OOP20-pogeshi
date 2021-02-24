package views;

import com.google.inject.Inject;


import javafx.application.Platform;
import models.GameMap.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import views.drawer.CanvasDrawer;
import views.drawer.Drawer;
import views.render.Render;
import views.scene.layout.LAYOUT;

public final class WorldMapView extends JavafxView {

    /**
     * standard layout to provide scene for this view.
     */
    public static final LAYOUT WORLD_LAYOUT = LAYOUT.WORLDMAP;


    private Drawer<Canvas> drawer;

    @Inject
    public WorldMapView(final Stage stage) {
        super(stage, WORLD_LAYOUT);
    }

    public void updateEntity(final Render render, final Point2D src, final Point2D dest) {
        if (this.drawer == null) {
            this.drawer = new CanvasDrawer((Canvas) this.getScene().lookup("#canvasMap"));
        }
        Platform.runLater(() -> this.drawer.draw(render, src, dest));
    }

}

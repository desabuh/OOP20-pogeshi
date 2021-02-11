package views;

import com.google.inject.Inject;

import javafx.application.Platform;
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
    //public static final LAYOUT WORLD_LAYOUT = LAYOUT.WORLDMAP;

    private Drawer<Canvas> drawer;

    //@Inject
    public WorldMapView(final Stage stage) {
        super(stage, LAYOUT.WORLDMAP);
    }

    public void updateEntity(final Render render) {
        this.drawer = new CanvasDrawer((Canvas) this.getScene().lookup("#canvasMap"));
        Platform.runLater(() -> this.drawer.draw(render));
    }




}

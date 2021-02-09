package views;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import views.drawer.CanvasDrawer;
import views.drawer.Drawer;
import views.render.Render;

public final class WorldMapView extends JavafxView {


    private Drawer<Canvas> drawer;

    public WorldMapView(final Stage stage) {
        super(stage);
        this.drawer = new CanvasDrawer((Canvas) this.getScene().lookup("#canvasMap"));
    }

    void updateEntity(final Render render) {
       Platform.runLater(() -> this.drawer.draw(render));
    }




}

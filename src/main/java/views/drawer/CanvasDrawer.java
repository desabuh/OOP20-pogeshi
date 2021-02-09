package views.drawer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import views.render.Render;

public final class CanvasDrawer implements Drawer<Canvas> {


    private Canvas canvas;

    public CanvasDrawer(final Canvas canvas) {
        this.canvas = canvas;
    }


    @Override
    public void draw(final Render render) {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRect(100, 100, render.getWidth(), render.getHeigth());
    }

}

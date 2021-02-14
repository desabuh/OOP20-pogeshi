package views.drawer;


import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import views.render.Render;

public final class CanvasDrawer implements Drawer<Canvas> {


    private Canvas canvas;
    private final BiMap<Point2D, Shape> shapeDisplayed = HashBiMap.create();
    private GraphicsContext gc;

    public CanvasDrawer(final Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }



    @Override
    public void draw(final Render render, final int x, final int y) {

        MovableShape shape = new MovableShapeImpl(
                new Rectangle(this.gc, render.getWidth(), render.getHeigth(), x, y));

        this.shapeDisplayed.put(new Point2D(x, y), shape);

    }

    @Override
    public void reDraw(final Point2D source, final Point2D destination) {

        if (this.shapeDisplayed.containsKey(source) && (this.shapeDisplayed.get(source) instanceof MovableShape)) {

            ((MovableShape) this.shapeDisplayed.get(source))
            .moveTo((int) destination.getX(), (int) destination.getY());
        }

    }
}

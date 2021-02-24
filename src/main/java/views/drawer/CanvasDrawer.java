package views.drawer;



import java.util.Map.Entry;


import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import models.GameMap.Point2D;
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

    private void clearShape(final Render render, final Point2D point) {
        if (render.getHeigth() == 0) {
            Shape shape = this.shapeDisplayed.get(point);
            shape.hideShape();
            this.shapeDisplayed.remove(point);
        }
    }

    @Override
    public void draw(final Render render, final Point2D source, final Point2D destination) {
        
        if (render.getWidth() == 0) {
            this.clearShape(render, destination);
        }
        else if (this.shapeDisplayed.containsKey(source)) {

            MovableShape shape = this.shapeDisplayed.entrySet().stream()
                    .filter(x -> x.getKey().equals(source))
                    .map(Entry::getValue)
                    .map(MovableShapeImpl::new)
                    .findAny()
                    .get();

            this.shapeDisplayed.remove(source);

            shape.moveTo((int) destination.getX(), (int) destination.getY());

            this.shapeDisplayed.put(destination, shape);

            this.shapeDisplayed.remove(source);

        }
        else {
            this.shapeDisplayed
            .put(destination, new Circle(gc, render, destination.getY(), destination.getX()));
        }

    }

}


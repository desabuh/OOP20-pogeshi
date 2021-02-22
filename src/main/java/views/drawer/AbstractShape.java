package views.drawer;

import models.GameMap.Point2D;
import models.GameMap.Point2DImp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class AbstractShape implements  views.drawer.Shape {

    protected int x;
    protected int y;
    protected int weight;
    protected int width;
    protected GraphicsContext gc;
    protected Color color;

    public AbstractShape(final GraphicsContext graphics, final int width, final int weight, final int y, final int x, final Color color ) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.width = width;
        this.gc = graphics;
        this.color = color;
        this.fillShape();
    }


    @Override
    public final Point2D getPosition() {
        return Point2DImp.setPoint(this.x, this.y);
    }

    @Override
    public final void setPosition(final Point2D position) {
        this.x = (int) position.getX();
        this.y = (int) position.getY();
    }

    public abstract void fillShape();

    public abstract void hideShape();

}

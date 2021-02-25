package views.drawer;

import models.GameMap.Point2D;

import models.GameMap.Point2DImp;
import views.render.Render;
import views.render.RenderFactory;
import views.render.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class AbstractShape implements  Shape, Renderable {

    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected GraphicsContext gc;
    protected Color color;

    public AbstractShape(final GraphicsContext graphics, final Render render, final int y, final int x) {
        this.x = x;
        this.y = y;
        this.accept(render);
        this.gc = graphics;
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

    @Override
    public final void accept(final Render render) {
        this.height = render.getHeigth();
        this.width = render.getWidth();
        this.color = render.getColor();
    }

    public abstract void fillShape();

    public abstract void hideShape();

}

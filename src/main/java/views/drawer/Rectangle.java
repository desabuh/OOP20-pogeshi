package views.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import views.render.Render;

public final class Rectangle extends AbstractShape {

    public Rectangle(final GraphicsContext graphics, final Render render, final int y, final int x) {
        super(graphics, render, y, x);
        this.fillShape();
    }

    @Override
    public void fillShape() {
        this.gc.setFill(this.color);
        this.gc.fillRect(y, x, width, height);
    }

    @Override
    public void hideShape() {
        this.gc.clearRect(y, x, width, height);
    }



}

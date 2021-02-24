package views.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import views.render.Render;

public final class Circle extends AbstractShape {

    public Circle(final GraphicsContext graphics, final Render render, final int y, final int x) {
        super(graphics, render, y, x);
    }

    @Override
    public void fillShape() {
        this.gc.setFill(this.color);
        this.gc.fillOval(y, x, this.width, this.width);
    }

    @Override
    public void hideShape() {
        this.gc.clearRect(y, x, width, width);
    }

}

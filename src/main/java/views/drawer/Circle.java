package views.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Circle extends AbstractShape {

    public Circle(final GraphicsContext graphics, final int radius, final int y, final int x, final Color color) {
        super(graphics, radius, radius, y, x, color);
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

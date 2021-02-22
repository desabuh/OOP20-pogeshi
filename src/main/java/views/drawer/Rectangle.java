package views.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Rectangle extends AbstractShape {

    public Rectangle(final GraphicsContext graphics, final int width, final int weight, final int y, final int x, final Color color) {
        super(graphics, width, weight, y, x, color);
        this.fillShape();
    }

    @Override
    public void fillShape() {
        this.gc.setFill(this.color);
        this.gc.fillRect(y, x, width, weight);
    }

    @Override
    public void hideShape() {
        this.gc.clearRect(y, x, width, weight);
    }



}

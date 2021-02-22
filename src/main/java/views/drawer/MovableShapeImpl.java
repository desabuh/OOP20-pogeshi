package views.drawer;

import models.GameMap.Point2D;
import models.GameMap.Point2DImp;

/**
 * 
 *implementation for {@link MovableShape}, decorator. for {@link Shape} movement functionality
 *
 */
public final class MovableShapeImpl implements MovableShape {

    private Shape decoratedShape;

    public MovableShapeImpl(final Shape shape) {
        this.decoratedShape = shape;
    }


    @Override
    public Point2D getPosition() {
        return this.decoratedShape.getPosition();
    }

    @Override
    public void setPosition(final Point2D position) {
        this.decoratedShape.setPosition(position);
    }

    @Override
    public void fillShape() {
        this.decoratedShape.fillShape();
    }

    @Override
    public void hideShape() {
        this.decoratedShape.hideShape(); 
    }

    /**
     * decorator method, update current position by:
     * -hide Shape visual rappresentation
     * -change its position
     * -fill the shape, giving him visibility in the target destination.
     */
    @Override
    public void moveTo(final int x, final int y) {
        this.hideShape();
        this.setPosition(Point2DImp.setPoint(x, y));
        this.fillShape();

    }


}

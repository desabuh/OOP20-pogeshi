package views.drawer;

import models.GameMap.Point2D;

/**
 * interface to provide basic functionality to a renderable shape.
 */
public interface Shape {

    /**
     * get its actual position.
     * the position rappresents the coordinate of the upper left bound of the shape.
     * @return actual position
     */
    Point2D getPosition();

    /**
     * set his position in the space.
     * @param position new position to update
     */
    void setPosition(Point2D position);

    /**
     * make shape visible by filling its renderable area.
     */
    void fillShape();

    /**
     * hide shape visualization.
     */
    void hideShape();

}

package models.GameMap;

public interface Point2D {

    /**
     * Return value of coordinate {@code X}.
     * @return  Value of X.
     */
    int getX();

    /**
     * Return value of coordinate {@code Y}.
     * @return  Value of Y.
     */
    int getY();

    /**
     * Set value of coordinate {@code X} and return a new {@code Point2D} with new {@code X} coordinate.
     * @param x     Value to be set for {@code X}.
     * @return      New {@code Point2D}.
     */
    Point2D setX(int x);

    /**
     * Set value of coordinate {@code Y} and return a new {@code Point2D} with new {@code Y} coordinate.
     * @param y     Value to be set for {@code Y}.
     * @return      New {@code Point2D}.
     */
    Point2D setY(int y);

    /**
     * Sum the x and y values to the {@code X} and {@code Y} coordinates of this {@code Point2D}
     * and return a new {@code Point2D} with new {@code X} and {@code Y} coordinates.
     * @param x     x value to add.
     * @param y     y value to add.
     * @return      New {@code Point2D}.
     */
    Point2D sum(int x, int y);

}

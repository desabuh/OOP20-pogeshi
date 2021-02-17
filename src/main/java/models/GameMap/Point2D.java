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
     * Set value of coordinate {@code X}.
     * @param x     Value to be set for {@code X}.
     */
    void setX(int x);

    /**
     * Set value of coordinate {@code Y}.
     * @param y     Value to be set for {@code Y}.
     */
    void setY(int y);

    /**
     * Set value of coordinate {@code X} and {@code Y}.
     * @param x     Value to be set for {@code X}.
     * @param y     Value to be set for {@code Y}.
     */
    void setBoth(int x, int y);

    /**
     * Sum the coordinates of {@code value} with the coordinates of this {@code Point2D}.
     * @param value     Point2D to add.
     */
    void sum(Point2D value);

    /**
     * Sum the x and y values to the {@code X} and {@code Y} coordinates of this {@code Point2D}.
     * @param x     x value to add.
     * @param y     y value to add.
     */
    void sum(int x, int y);
}

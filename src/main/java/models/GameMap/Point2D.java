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
    Point2D setX(int x);

    /**
     * Set value of coordinate {@code Y}.
     * @param y     Value to be set for {@code Y}.
     */
    Point2D setY(int y);

    /**
     * Sum the x and y values to the {@code X} and {@code Y} coordinates of this {@code Point2D}.
     * @param x     x value to add.
     * @param y     y value to add.
     */
    Point2D sum(int x, int y);
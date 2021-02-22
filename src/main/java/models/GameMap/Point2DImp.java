package models.GameMap;

/**
 * 
 * immutable class to provide basic operation with 2D coordinate points, implements {@link Point2D}.
 *
 */
public final class Point2DImp implements Point2D {

    /**
     * x axis coordinate.
     */
    private int x;

    /**
     * y axis coordinate.
     */
    private int y;

    /**
     * static factory to get a new {@code Point2D} based on its coordinate.
     * @param x
     * @param y
     * @return {@code Point2D} new {@code Point2D} instance 
     */
    public static Point2D setPoint(final int x, final int y) {
        return new Point2DImp(x, y);
    }

    /**
     * static function to provide a new sum-wise Point2D. 
     * @param point1
     * @param point2
     * @return Point2D new Point2D instance
     */
    public static Point2D sumPoint2d(final Point2D point1, final Point2D point2) {
        return setPoint(point1.getX() + point2.getX(), point1.getY() + point2.getY());
    }

    /**
     * Set {@code this.X} and {@code this.Y} equals to x and y.
     * @param x
     * @param y
     */
    public Point2DImp(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return {@code X}.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Return {@code Y}.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Set {@code this.X} equal to x.
     */
    @Override
    public Point2D setX(final int x) {
        return new Point2DImp(x, this.y);
    }

    /**
     * Set {@code this.Y} equal to y.
     */
    @Override
    public Point2D setY(final int y) {
        return new Point2DImp(this.x, y);
    }

    /**
     * Combine in a sum two Point2D for the applied changes in respective coordinates.
     */
    @Override
    public Point2D sum(final int x, final int y) {
        return setPoint(this.getX() + x, this.getY() + y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point2DImp other = (Point2DImp) obj;
        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Point2DImp [x=" + x + ", y=" + y + "]";
    }

}


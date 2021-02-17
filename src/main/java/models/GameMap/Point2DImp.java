package models.GameMap;

public final class Point2DImp implements Point2D {

    private int x;
    private int y;

    /**
     * Set {@code this.X} and {@code this.Y} equals to x and y.
     * @param x
     * @param y
     */
    public Point2DImp(final int x, final int y) {
        setBoth(x, y);
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
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Set {@code this.Y} equal to y.
     */
    @Override
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * Set {@code this.X} and {@code this.y} equals to x and y.
     */
    @Override
    public void setBoth(final int x, final int y) {
        this.x = x;
        this.y = y;

    }

    /**
     * Add the x and y values of {@code value} to {@code this.X} and {@code this.Y}.
     */
    @Override
    public void sum(final Point2D value) {
        sum(value.getX(), value.getY());
    }

    /**
     * Add the x and y values to {@code this.X} and {@code this.Y}.
     */
    @Override
    public void sum(final int x, final int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

}

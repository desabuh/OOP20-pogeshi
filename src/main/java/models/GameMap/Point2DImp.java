package models;

public final class Point2DImp implements Point2D {

    private int x;
    private int y;

    public Point2DImp(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(final int x) {
        this.x = x;
    }

    @Override
    public void setY(final int y) {
        this.y = y;
    }

    @Override
    public void setBoth(final int x, final int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public void sum(final Point2D value) {
        sum(value.getX(), value.getY());
    }

    @Override
    public void sum(final int x, final int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

}

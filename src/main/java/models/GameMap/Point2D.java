package models;

public interface Point2D {

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    void setBoth(int x, int y);

    void sum(Point2D value);

    void sum(int x, int y);
}

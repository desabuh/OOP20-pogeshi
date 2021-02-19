package models.GameMap;

public interface Point2D {

    int getX();

    int getY();

    Point2D setX(int x);

    Point2D setY(int y);

    Point2D sum(int x, int y);
}

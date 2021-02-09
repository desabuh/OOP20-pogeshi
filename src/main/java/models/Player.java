package models;

public interface Player extends Character {

    void setPosition(Point2D destination);

    int getMana();

    Hand getHand();

    void setMana(int value); //TODO: discuss with others, not in project?

}

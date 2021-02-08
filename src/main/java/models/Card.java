package models;

/**
 * Interface that represent a card of a deck.
 */

public interface Card {

    int getCost();

    int getAttack();

    int getDefense();

    String getName();

    String getResourcePath();
}
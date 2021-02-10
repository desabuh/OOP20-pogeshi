package models;

/**
 * Interface that represent a card of a deck.
 */

public interface Card {

    int getCost();

    int getAttack();

    int getShield();

    String getName();

    String getResourcePath();

    String getDescription();
}

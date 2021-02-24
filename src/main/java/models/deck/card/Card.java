package models.deck.card;

/**
 * Interface representing a card of a deck.
 */

public interface Card {

    /**
     * Gets the cost.
     *
     * @return The cost of this card
     */
    int getCost();

    /**
     * Gets the attack.
     *
     * @return The attack of this card
     */
    int getAttack();

    /**
     * Gets the shield.
     *
     * @return The shield of this card
     */
    int getShield();

    /**
     * Gets the name.
     *
     * @return The name of this card
     */
    String getName();

    /**
     * Gets the resource path.
     *
     * @return The name of this card
     */
    String getResourcePath();

    /**
     * Gets the description.
     *
     * @return The Description of this card
     */
    String getDescription();
}

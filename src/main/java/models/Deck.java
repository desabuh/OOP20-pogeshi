package models;

import java.util.List;
import java.util.Optional;

/**
 *  Interface representing a deck of cards.
 */

public interface Deck {

    /**
     * Pop a card from deck.
     *
     * @return the Card wrapped in an {@link Optional}
     */
    Optional<Card> popCard();

    /**
     * Gets the last card inserted in the deck.
     *
     * @return the Card wrapped in an {@link Optional}
     */
    Optional<Card> getCard();

    /**
     * Gets the cards of the deck.
     *
     * @return the cards of the deck
     */
    List<Card> getCards();

    /**
     * Removes the card from the deck.
     *
     * @param card the card to remove
     */
    void removeCard(Card card);

    /**
     * Adds the card to the deck.
     *
     * @param card the card to add
     */
    void addCard(Card card);

    /**
     * Checks if the deck is full.
     *
     * @return true, if the deck is full
     */
    boolean isDeckFull();

    /**
     * Checks if the card is in deck.
     *
     * @param card the card to check
     * @return true, if the card is in the deck
     */
    boolean isCardInDeck(Card card);
}

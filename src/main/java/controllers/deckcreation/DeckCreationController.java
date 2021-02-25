package controllers.deckcreation;

import controllers.Controller;

/**
 * Interface representing a Deck creation controller.
 */
public interface DeckCreationController extends Controller {

    /**
     * Changes the displayed card.
     */
    void changeCardDescription();

    /**
     * Remove a card from the deck list.
     */
    void removeCardFromDeck();

    /**
     * Add a card to the deck list.
     */
    void addCardToDeck();

    /**
     * Save the deck created.
     */
    void saveDeck();
}

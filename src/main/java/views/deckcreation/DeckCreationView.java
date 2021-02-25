package views.deckcreation;

import java.util.List;

import models.deck.card.Card;
import views.View;

/**
 * Interface for a deck view.
 */
public interface DeckCreationView extends View {

    /**
     * Initialize the view.
     * 
     * @param remainingCards the player's cards that aren't in the deck
     * @param playerDeck the player's deck
     */
    void initialize(List<Card> remainingCards, List<Card> playerDeck);

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
}

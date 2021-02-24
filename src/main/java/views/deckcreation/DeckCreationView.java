package views.deckcreation;

import java.util.List;

import models.deck.card.Card;
import views.View;

public interface DeckCreationView extends View {

    /**
     * Initialize the view.
     * 
     * @param cards all the cards in the game
     * @param playerDeck the deck of the player
     */
    void initialize(List<Card> cards, List<Card> playerDeck);

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

package views;

import models.Card;

public interface DeckCreationView {
    void removeCardFromDeck(Card card);

    void addCardToDeck(Card card);

    void changeCardDisplayed(Card card);
}

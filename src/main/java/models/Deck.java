package models;

import java.util.List;
import java.util.Optional;

/**
 *  Interface that represent a deck of  10 cards.
 */

public interface Deck {
    Optional<Card> popCard();

    Optional<Card> getCard();

    List<Card> getCards();

    void removeCard(Card card);

    void addCard(Card card);

    boolean isDeckFull();

    boolean isCardInDeck(Card card);
}

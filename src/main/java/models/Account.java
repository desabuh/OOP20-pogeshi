package models;

import java.util.List;

public interface Account {

    Deck getDeck();

    List<Card> getRemainingCards();

    Statistics getStatistics();

    void win();

    void lose();

    void addCardToDeck(Card card);

    void removeCardFromDeck(Card card);

    void save();

}

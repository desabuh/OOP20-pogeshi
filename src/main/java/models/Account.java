package models;

import java.util.List;

public interface Account {

    Deck getDeck();

    List<Card> getRemainingCards(); //TODO: ask if handling this with a Deck for the ramaining cards is better

    Statistics getStatistics();

    void win();

    void lose();

    void addCardToDeck(Card card);

    void removeCardFromDeck(Card card); //TODO: notify Jolla about the name change from "removeCardToDeck" to "removeCardFromDeck"

}

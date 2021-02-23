package models;

import java.util.List;

import models.deck.card.Card;

public interface Hand {
    List<Card> getCards();

    void addCard(Card c);

    void removeCard(int index);
}

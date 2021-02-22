package models.battle;

import java.util.List;

import models.Card;

public interface Hand {
    List<Card> getCards();

    void addCard(Card c);

    void removeCard(int index);
}

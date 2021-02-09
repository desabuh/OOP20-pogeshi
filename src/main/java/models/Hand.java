package models;

import java.util.List;

public interface Hand {
    List<Card> getCards();

    void addCard(Card c);

    void removeCard(int index);
}

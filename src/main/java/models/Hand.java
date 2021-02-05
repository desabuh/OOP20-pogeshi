package models;

import java.util.List;

import controllers.Card;

public interface Hand {
    public List<Card> getCards();
    
    public void addCard(Card c);
    
    public void removeCard(int index);
}

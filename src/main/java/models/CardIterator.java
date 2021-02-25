package models;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import models.deck.card.Card;


public final class CardIterator implements Iterator<Card> {

    private final Collection<Card> deck;
    private Collection<Card> shuffledDeck;
    private Iterator<Card> iterator;


    public CardIterator(final Collection<Card> deck) {
        this.deck = deck;
        this.shuffledDeck = this.deck;
        this.iterator = this.getIterator();
    }

    private Iterator<Card> getIterator() {
        Collections.shuffle((List<Card>) this.shuffledDeck);
        return this.shuffledDeck.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public Card next() {
        this.iterator =  this.hasNext() ? this.iterator : this.getIterator();
        return this.iterator.next();
    }

}

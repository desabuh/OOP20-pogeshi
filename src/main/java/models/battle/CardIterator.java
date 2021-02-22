package models.battle;

import java.util.Collection;
import java.util.Iterator;

import models.Card;

public final class CardIterator implements Iterator<Card> {

    private final Collection<Card> deck;
    private Iterator<Card> iterator;


    public CardIterator(final Collection<Card> deck) {
        this.deck = deck;
        this.iterator = this.getIterator();
    }

    private Iterator<Card> getIterator() {
        return this.deck.iterator();
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

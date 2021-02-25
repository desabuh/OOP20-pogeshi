package models;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import models.deck.card.Card;


public final class CardIterator implements Iterator<Card> {
    /**
     * The deck that will be referenced to create a shuffled one. This will never be changed.
     * */
    private final Collection<Card> deck;
    /**
     * The shuffled deck that will be used to get cards from.
     * */
    private Collection<Card> shuffledDeck;
    /**
     * The iterator on the shuffled deck.
     * */
    private Iterator<Card> iterator;

    /**
     * CardIterator constructor. Will generate an iterator over a given deck.
     * @param deck The collection to iterate.
     * */
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

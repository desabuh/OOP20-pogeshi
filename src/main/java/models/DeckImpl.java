package models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public final class DeckImpl implements Deck {
    private LinkedList<Card> cards = new LinkedList<>();

    @Override
    public Optional<Card> popCard() {
        if (this.cards.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(this.cards.pop());
        }
    }

    @Override
    public Optional<Card> getCard() {
        if (this.cards.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(this.cards.getFirst());
        }
    }

    @Override
    public List<Card> getCards() {
        if (this.cards.isEmpty()) {
            return Collections.emptyList();
        } else {
            return this.cards;
        }
    }

    @Override
    public void removeCard(final Card card) {
        if (!this.cards.isEmpty() && checkCardInDeck(card)) {
            this.cards.remove(card);
        }
    }

    @Override
    public void addCard(final Card card) {
        if (this.cards.size() < 10) {
            this.cards.add(card);
        }
    }

    @Override
    public boolean checkDeckFull() {
        return this.cards.size() == 10;
    }

    @Override
    public boolean checkCardInDeck(final Card card) {
        return this.cards.contains(card);
    }
}

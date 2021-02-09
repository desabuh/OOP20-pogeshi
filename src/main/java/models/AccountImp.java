package models;

import java.util.ArrayList;
import java.util.List;

public final class AccountImp implements Account {

    private Deck deck;
    private List<Card> remainingCards;
    private Statistics statistics;

    public AccountImp() {
        this.deck = new Deck(); //TODO: Load deck from file.
        //TODO: IF (file exist); THEN {load deck from file}; ELSE {create new file and generate default deck};
        this.remainingCards = new ArrayList<Card>(); //TODO: Load remainingCards from file.
        //TODO: IF (file exist); THEN {load remainingCards from file}; ELSE {create new file (maybe generate default remainingCards)};
        this.statistics = new Statistics(); //TODO: Load statistics from file.
        //TODO: IF (file exist); THEN {load statistics from file}; ELSE {create new file and set default statistics};
    }

    @Override
    public Deck getDeck() {
        return this.deck;
    }

    @Override
    public List<Card> getRemainingCards() {
        return this.remainingCards;
    }

    @Override
    public Statistics getStatistics() {
        return this.statistics;
    }
    //TODO: not sure how to do? How do i generate a random Card?
    @Override
    public void win() {
        Card card = new Card(); //TODO: generate random card
        if (this.deck.getCards().contains(card) || remainingCards.contains(card)) {
            statistics.updateOnWin(true);
        } else {
            statistics.updateOnWin(false);
        }
        this.remainingCards.add(card);
    }

    @Override
    public void lose() {
        statistics.updateOnLose();
    }

    @Override
    public void addCardToDeck(final Card card) {
        if (remainingCards.contains(card)) {
            remainingCards.remove(card);
            deck.addCard(card);
        }
    }

    @Override
    public void removeCardFromDeck(final Card card) {
        this.deck.removeCard(card);
        remainingCards.add(card);
    }

}

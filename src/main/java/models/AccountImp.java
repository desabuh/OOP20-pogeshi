package models;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public final class AccountImp implements Account {

    private Deck deck;
    private List<Card> remainingCards;
    private Statistics statistics;
    private static final String SAVES_PATH = "res\\saves\\";
    private static final String JSONS_PATH = "res\\jsons\\";
    private static final Type LINKED_LIST_TYPE = new TypeToken<LinkedList<CardImpl>>() { }.getType();
    private final FileManagerImp<LinkedList<Card>> fileDeck;
    private final FileManagerImp<LinkedList<Card>> fileRemainingCards;
    private final FileManagerImp<Statistics> fileStatistics;
    private final FileManagerImp<LinkedList<Card>> fileDefaultDeck;

    public AccountImp() {

        this.fileDeck = new FileManagerImp<LinkedList<Card>>(SAVES_PATH + "Deck.json");
        this.fileRemainingCards = new FileManagerImp<LinkedList<Card>>(SAVES_PATH + "RemainingCards.json");
        this.fileStatistics = new FileManagerImp<Statistics>(SAVES_PATH + "Statistics.json");
        this.fileDefaultDeck = new FileManagerImp<LinkedList<Card>>(JSONS_PATH + "DefaultDeck.json");

        try {
            if (!this.fileDeck.fileExist()) {
                if (this.fileDefaultDeck.fileExist()) {
                    this.fileDeck.save(this.fileDefaultDeck.load(LINKED_LIST_TYPE));
                } else {
                    throw new IOException("DefaultDeck.json is missing");
                }
            }
            this.deck = new DeckImpl(this.fileDeck.load(LINKED_LIST_TYPE));
            if (!this.fileRemainingCards.fileExist()) {
                this.fileRemainingCards.save(new LinkedList<Card>());
            }
            this.remainingCards = this.fileRemainingCards.load(LINKED_LIST_TYPE);
            if (!fileStatistics.fileExist()) {
                this.fileStatistics.save(new StatisticsImp(0, 0, 10));
            }
            this.statistics = fileStatistics.load(StatisticsImp.class);
        } catch (IOException e) {
         // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //TODO: to test
    @Override
    public Deck getDeck() {
        return this.deck;
    }
    //TODO: to test
    @Override
    public List<Card> getRemainingCards() {
        return this.remainingCards;
    }
    //TODO: to test
    @Override
    public Statistics getStatistics() {
        return this.statistics;
    }
    //TODO: not sure how to do? How do i generate a random Card?
    @Override
    public void win() {
        Card card = new Card(); //TODO: generate random card
        if (this.deck.getCards().contains(card) || remainingCards.contains(card)) {
            this.statistics.updateOnWin(true);
        } else {
            this.statistics.updateOnWin(false);
        }
        this.remainingCards.add(card);
        this.fileStatistics.save(this.statistics);
        this.fileRemainingCards.save((LinkedList<Card>) this.remainingCards);
    }

    @Override
    public void lose() {
        this.statistics.updateOnLose();
        this.fileStatistics.save(this.statistics);
    }

    @Override
    public void addCardToDeck(final Card card) {
        if (this.remainingCards.contains(card) && !this.deck.isDeckFull()) {
            this.remainingCards.remove(card);
            this.deck.addCard(card);
        }
    }

    @Override
    public void removeCardFromDeck(final Card card) {
        if (this.deck.isCardInDeck(card)) {
            this.deck.removeCard(card);
            this.remainingCards.add(card);
        }
    }

    @Override
    public void save() {
        this.fileDeck.save((LinkedList<Card>) this.deck.getCards());
        this.fileRemainingCards.save((LinkedList<Card>) this.remainingCards);
    }

    @Override
    public void deleteSaves() {
        try {
            this.fileDeck.save(this.fileDefaultDeck.load(LINKED_LIST_TYPE));
            this.deck = new DeckImpl(this.fileDeck.load(LINKED_LIST_TYPE));
            this.fileRemainingCards.save(new LinkedList<Card>());
            this.remainingCards = this.fileRemainingCards.load(LINKED_LIST_TYPE);
            this.fileStatistics.save(new StatisticsImp(0, 0, 10));
            this.statistics = this.fileStatistics.load(StatisticsImp.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

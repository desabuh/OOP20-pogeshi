package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public final class AccountImp implements Account {

    private Deck deck;
    private List<Card> remainingCards;
    private Statistics statistics;
    private static final String SAVES_PATH = "..\\..\\..\\res\\saves\\";

    //TODO: Do separated files for Deck, RemainingCards, Statistics, AllCards because it's easier to add new things to it.
    public AccountImp() throws IOException {
        File deckFile = new File(SAVES_PATH + "Deck.json");
        if (deckFile.exists()) {
            loadDeckSaves();
        } else {
            deckFile.createNewFile();
            //this.deck = new Deck(); 
            //TODO: Set deck to default values
            saveDeck();
        }
        File remainingCardsFile = new File(SAVES_PATH + "RemainingCards.json");
        if (remainingCardsFile.exists()) {
            loadRemainingCardsSaves();
        } else {
            remainingCardsFile.createNewFile();
            this.remainingCards = new ArrayList<Card>(); 
            saveRemainingCards();
        }
        File statisticsFile = new File(SAVES_PATH + "Statistics.json");
        if (statisticsFile.exists()) {
            loadStatisticsSaves();
        } else {
            statisticsFile.createNewFile();
            this.statistics = new StatisticsImp(); 
            saveStatistics();
        }
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

    public void save() {
        saveDeck();
        saveRemainingCards();
    }

    private void loadDeckSaves() {
     // TODO Auto-generated method stub
    }

    private void loadRemainingCardsSaves() {
        // TODO Auto-generated method stub

    }

    private void loadStatisticsSaves() {
        // TODO Auto-generated method stub

    }

    private void saveDeck() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = new FileWriter(SAVES_PATH + "");
            //String json = gson.toJson(this.deck);
            JsonElement json = gson.toJsonTree(this.deck);
            gson.toJson(json, writer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void saveRemainingCards() {
        // TODO Auto-generated method stub

    }

    private void saveStatistics() {
        // TODO Auto-generated method stub

    }

}

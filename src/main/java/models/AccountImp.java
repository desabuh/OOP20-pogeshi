package models;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public final class AccountImp implements Account {

    private Deck deck;
    private List<Card> remainingCards;
    private Statistics statistics;
    private static final String SAVES_PATH = "res\\saves\\";
    private static final String JSONS_PATH = "res\\jsons\\";
    private final Gson gson;

    //TODO: code it  again in a way for not repeating code (if you have time)
    public AccountImp() {

        gson = new GsonBuilder().setPrettyPrinting().create();
        File deckFile = new File(SAVES_PATH + "Deck.json");

        try {
            if (deckFile.exists()) {
                loadDeckSaves();
            } else {
                createSavesDeck();
            }
            File remainingCardsFile = new File(SAVES_PATH + "RemainingCards.json");
            if (remainingCardsFile.exists()) {
                loadRemainingCardsSaves();
            } else {
                createSavesRemainingCards();
            }
            File statisticsFile = new File(SAVES_PATH + "Statistics.json");
            if (statisticsFile.exists()) {
                loadStatisticsSaves();
            } else {
                createSavesStatistics();
            }
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
            statistics.updateOnWin(true);
        } else {
            statistics.updateOnWin(false);
        }
        this.remainingCards.add(card);
    }
    //TODO: to test
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
    //TODO: to test
    @Override
    public void removeCardFromDeck(final Card card) {
        this.deck.removeCard(card);
        remainingCards.add(card);
    }
    //TODO: to test
    @Override
    public void save() {
        try {
            saveDeck();
            saveRemainingCards();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteSaves() {
        try {
            File file = new File(SAVES_PATH + "Deck.json");
            deleteFile(file);
            file = new File(SAVES_PATH + "RemainingCards.json");
            deleteFile(file);
            file = new File(SAVES_PATH + "Statistics.json");
            deleteFile(file);
            createSaves();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createSaves() throws IOException {
        createSavesDeck();
        createSavesRemainingCards();
        createSavesStatistics();
    }

    private void createSavesDeck() throws IOException {
        File file = new File(SAVES_PATH + "Deck.json");
        createFile(file);
        loadDefaultDeck();
        saveDeck();
    }

    private void createSavesRemainingCards() throws IOException {
        File file = new File(SAVES_PATH + "RemainingCards.json");
        createFile(file);
        this.remainingCards = new LinkedList<Card>();
        saveRemainingCards();
    }

    private void createSavesStatistics() throws IOException {
        File file = new File(SAVES_PATH + "Statistics.json");
        createFile(file);
        this.statistics = new StatisticsImp();
        saveStatistics();
}

    private void loadDeckSaves() throws IOException {
        FileReader reader = new FileReader(SAVES_PATH + "Deck.json");
        Type linkedListType = new TypeToken<LinkedList<CardImpl>>() { }.getType();
        List<Card> cards = gson.fromJson(reader, linkedListType);
        this.deck = new DeckImpl(cards);
        reader.close();
    }
    //TODO: change DefaultDeck.json later with real default deck
    private void loadDefaultDeck() throws IOException {
        FileReader reader = new FileReader(JSONS_PATH + "DefaultDeck.json");
        Type linkedListType = new TypeToken<LinkedList<CardImpl>>() { }.getType();
        List<Card> cards = gson.fromJson(reader, linkedListType);
        this.deck = new DeckImpl(cards);
        reader.close();
    }

    private void loadRemainingCardsSaves() throws IOException {
        FileReader reader = new FileReader(SAVES_PATH + "RemainingCards.json");
        Type linkedListType = new TypeToken<LinkedList<CardImpl>>() { }.getType();
        this.remainingCards = gson.fromJson(reader, linkedListType);
        reader.close();
    }

    private void loadStatisticsSaves() throws IOException {
        FileReader reader = new FileReader(SAVES_PATH + "Statistics.json");
        this.statistics = gson.fromJson(reader, StatisticsImp.class);
        reader.close();
    }

    private void saveDeck() throws IOException {
        FileWriter writer = new FileWriter(SAVES_PATH + "Deck.json");
        gson.toJson(this.deck.getCards(), writer);
        writer.close();
    }

    private void saveRemainingCards() throws IOException {
        FileWriter writer = new FileWriter(SAVES_PATH + "RemainingCards.json");
        gson.toJson(this.remainingCards, writer);
        writer.close();
    }

    private void saveStatistics() throws IOException {
        FileWriter writer = new FileWriter(SAVES_PATH + "Statistics.json");
        gson.toJson(this.statistics, writer);
        writer.close();
    }

    private void createFile(final File file) throws IOException {
        if (!file.createNewFile()) {
            System.err.println("Failed to create the file");
        }
    }

    private void deleteFile(final File file) throws IOException {
        if (!file.delete()) {
            System.err.println("Failed to delete the file");
        }
    }

}

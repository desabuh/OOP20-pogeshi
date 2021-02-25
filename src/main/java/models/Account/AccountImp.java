package models.Account;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import models.Account.FileManager.FileManagerImp;
import models.deck.Deck;
import models.deck.DeckImpl;
import models.deck.card.Card;
import models.deck.card.CardImpl;

public final class AccountImp implements Account {

    private Deck deck;
    private List<Card> remainingCards;
    private Statistics statistics;
    private static final String SAVES_PATH = "saves" + File.separator;
    private static final Type LINKED_LIST_TYPE = new TypeToken<LinkedList<CardImpl>>() { }.getType();
    private final FileManagerImp<LinkedList<Card>> fileDeck;
    private final FileManagerImp<LinkedList<Card>> fileRemainingCards;
    private final FileManagerImp<Statistics> fileStatistics;
    private final Random random;

    /**
     * Load default saves if existing or create default ones.
     */
    public AccountImp() {

        random = new Random();
        this.fileDeck = new FileManagerImp<LinkedList<Card>>(SAVES_PATH + "Deck.json");
        this.fileRemainingCards = new FileManagerImp<LinkedList<Card>>(SAVES_PATH + "RemainingCards.json");
        this.fileStatistics = new FileManagerImp<Statistics>(SAVES_PATH + "Statistics.json");
        loadSaves();
    }

    /**
     * Return the value of {@code deck}.
     */
    @Override
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Return a {@code List<Card>} of the {@code remaining cards}.
     */
    @Override
    public List<Card> getRemainingCards() {
        return this.remainingCards;
    }

    /**
     * Return a {@code obj statistics} of the statistics of the {@code Account}.
     */
    @Override
    public Statistics getStatistics() {
        return this.statistics;
    }

    /**
     * Load all the {@code cards} from file and then select one at random.
     * After checking if the {@code deck} or the {@code remaining cards} already have
     * the selected card, the card get added to the {@code remaining cards} and the
     * {@code statistics} get updated.
     */
    @Override
    public void win() throws IOException {
        try {
            final LinkedList<Card> allCards = loadDefaultData("ListOfCards.json");
            final Card card = allCards.get(random.nextInt(allCards.size()));
            if (this.deck.getCards().contains(card) || this.remainingCards.contains(card)) {
                this.statistics.updateOnWin(true);
            } else {
                this.statistics.updateOnWin(false);
            }
            this.remainingCards.add(card);
            this.fileStatistics.save(this.statistics);
            this.fileRemainingCards.save((LinkedList<Card>) this.remainingCards);
        } catch (IOException e) {
            throw new IOException("ListOfCards.json is missing");
        }
    }

    /**
     * Update the loses of the {@code statistics}.
     */
    @Override
    public void lose() {
        this.statistics.updateOnLose();
        this.fileStatistics.save(this.statistics);
    }

    /**
     * Add {@code card} to the {@code deck},
     * and remove it from the {@code remaining cards}.
     */
    @Override
    public void addCardToDeck(final Card card) {
        if (this.remainingCards.contains(card) && !this.deck.isDeckFull()) {
            this.remainingCards.remove(card);
            this.deck.addCard(card);
        }
    }

    /**
     * Remove {@code card} from the {@code deck},
     * and add it in the {@code deck}.
     */
    @Override
    public void removeCardFromDeck(final Card card) {
        if (this.deck.isCardInDeck(card)) {
            this.deck.removeCard(card);
            this.remainingCards.add(card);
        }
    }

    /**
     * Save the {@code deck} and the {@code remaining cards} on file.
     */
    @Override
    public void save() {
        this.fileDeck.save((LinkedList<Card>) this.deck.getCards());
        this.fileRemainingCards.save((LinkedList<Card>) this.remainingCards);
    }

    /**
     * Overwrite all saves with the default ones.
     */
    @Override
    public void deleteSaves() {
        try {
            this.fileDeck.save(loadDefaultData("DefaultDeck.json"));
            this.deck = new DeckImpl(this.fileDeck.load(LINKED_LIST_TYPE));
            this.fileRemainingCards.save(new LinkedList<Card>());
            this.remainingCards = this.fileRemainingCards.load(LINKED_LIST_TYPE);
            this.fileStatistics.save(new StatisticsImp(0, 0, this.deck.getCards().size()));
            this.statistics = this.fileStatistics.load(StatisticsImp.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load all saves and overwrite the current data.
     */
    public void loadSaves() {
        try {
            File file = new File(SAVES_PATH);
            if (!file.exists()) {
                if (!file.mkdir()) {
                    throw new IOException("Could not create the directory.");
                }
            }
            if (!this.fileDeck.fileExist()) {
                this.fileDeck.save(loadDefaultData("DefaultDeck.json"));
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
            e.printStackTrace();
        }
    }

    private LinkedList<Card> loadDefaultData(final String fileName) throws IOException {
        try {
            Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/jsons/" + fileName));
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            LinkedList<Card> result = gson.fromJson(reader, LINKED_LIST_TYPE);
            reader.close();
            return result;
        } catch (IOException e) {
            throw new IOException("Error while closing reader.");
        }
    }

}

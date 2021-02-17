package models.Account;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import models.Card;
import models.CardImpl;
import models.Deck;
import models.DeckImpl;
import models.Account.FileManager.FileManager;
import models.Account.FileManager.FileManagerImp;

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

    /**
     * Load default saves if existing or create default ones.
     */
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

    /**
     * Return the value of {@code deck}.
     */
    //TODO: to test
    @Override
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Return a {@code List<Card>} of the {@code remaining cards}.
     */
    //TODO: to test
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
    //TODO: to test
    @Override
    public void win() throws IOException {
        try {
            final FileManager<LinkedList<Card>> fileAllCards = new FileManagerImp<LinkedList<Card>>(JSONS_PATH + "ListOfCards.json");
            final LinkedList<Card> allCards = fileAllCards.load(LINKED_LIST_TYPE);
            final Random random = new Random();
            final Card card = allCards.get(random.nextInt(allCards.size()));
            if (this.deck.getCards().contains(card) || remainingCards.contains(card)) {
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
     * Save the {@code deck} and the {@code remaing cards} on file.
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

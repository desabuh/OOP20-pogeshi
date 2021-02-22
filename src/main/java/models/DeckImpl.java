package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * A {@link models.Deck} implementation.
 */
public final class DeckImpl implements Deck {

    private static final int NUMBER_OF_DECK_CARDS = 10;
    private LinkedList<Card> cards = new LinkedList<>();

    /**
     * Instantiates a standard deck.
     */
    public DeckImpl() {
        Gson gson = new Gson();
        try (FileReader fReader = new FileReader("res" + File.separator + "jsons" + File.separator + "ListOfCards.json")) {
            Type t = new TypeToken<LinkedList<CardImpl>>() { }.getType();
            this.cards = gson.fromJson(fReader, t);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = this.cards.size(); i > NUMBER_OF_DECK_CARDS; i--) {
            this.cards.remove(i - 1);
        }
    }

    /**
     * Instantiates a new deck from a list of cards.
     *
     * @param cards the cards of the deck
     */
    public DeckImpl(final LinkedList<Card> cards) {
        this.cards = (LinkedList<Card>) cards;
    }

    public DeckImpl(final FileReader file) {
        Gson gson = new Gson();
        try (file){
            Type t = new TypeToken<LinkedList<CardImpl>>() { }.getType();
            this.cards = gson.fromJson(file, t);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = this.cards.size(); i > NUMBER_OF_DECK_CARDS; i--) {
            this.cards.remove(i - 1);
        }
    }

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
        if (!this.cards.isEmpty() && isCardInDeck(card)) {
            this.cards.remove(card);
        }
    }

    @Override
    public void addCard(final Card card) {
        if (this.cards.size() < NUMBER_OF_DECK_CARDS) {
            this.cards.add(card);
        }
    }

    @Override
    public boolean isDeckFull() {
        return this.cards.size() == NUMBER_OF_DECK_CARDS;
    }

    @Override
    public boolean isCardInDeck(final Card card) {
        return this.cards.contains(card);
    }

    @Override
    public String toString() {
        return "cards = " + cards;
    }
}

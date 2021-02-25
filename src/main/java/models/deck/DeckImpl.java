package models.deck;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.deck.card.Card;
import models.deck.card.CardImpl;

/**
 * A {@link models.deck.Deck} implementation.
 */
public final class DeckImpl implements Deck {

    private static final Type LINKED_LIST_TYPE = new TypeToken<LinkedList<CardImpl>>() { }.getType();
    private static final int NUMBER_OF_DECK_CARDS = 10;
    private final LinkedList<Card> cards;

    /**
     * Instantiates a standard deck.
     */
    @SuppressWarnings("serial")
    public DeckImpl() {
        this.cards  = new LinkedList<>();
        try {
            this.cards.addAll(loadDefaultData("ListOfCards.json"));
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
    public DeckImpl(final List<Card> cards) {
        this.cards = new LinkedList<>();
        this.cards.addAll(cards);
    }

    /**
     * Instantiates a new deck from a file.
     * 
     * @param file the FileReader that contains the file that contains the deck
     */
    @SuppressWarnings("serial")
    public DeckImpl(final FileReader file) {
        this.cards = new LinkedList<>();
        final Gson gson = new Gson();
        try (file) {
            final Type t = new TypeToken<List<CardImpl>>() { }.getType();
            this.cards.addAll(gson.fromJson(file, t));
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

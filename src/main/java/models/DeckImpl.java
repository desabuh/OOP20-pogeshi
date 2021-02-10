package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public final class DeckImpl implements Deck {
    private LinkedList<Card> cards = new LinkedList<>();

    public DeckImpl() {
        Gson gson = new Gson();
        try {
            Type t = new TypeToken<LinkedList<CardImpl>>() { }.getType();
            this.cards = gson.fromJson(new FileReader("res" + File.separator + "jsons" + File.separator + "ListOfCards.json"), t);
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = (this.cards.size() - 1); i > 9 ; i--) {
            this.cards.remove(i);
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
        if (this.cards.size() < 10) {
            this.cards.add(card);
        }
    }

    @Override
    public boolean isDeckFull() {
        return this.cards.size() == 10;
    }

    @Override
    public boolean isCardInDeck(final Card card) {
        return this.cards.contains(card);
    }
}

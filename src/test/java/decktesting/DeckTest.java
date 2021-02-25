package decktesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import models.deck.Deck;
import models.deck.DeckImpl;
import models.deck.card.Card;
import models.deck.card.CardImpl;

class DeckTest {
    private Deck deck;

    @Test
    public void testDeckCreation() {
        try {
            this.deck = new DeckImpl(new FileReader("res" + File.separator + "jsons" + File.separator + "DefaultDeck.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(this.deck.isDeckFull());

        while (!this.deck.getCards().isEmpty()) {
            this.deck.popCard();
        }
        assertFalse(this.deck.isDeckFull());

        this.deck = new DeckImpl();

        assertTrue(this.deck.isDeckFull());
    }

    @Test
    public void testAddCard() {
        Card card1;
        try {
            card1 = new CardImpl.Builder()
                                .name("Monster")
                                .attack(1)
                                .shield(2)
                                .cost(2)
                                .description("Description")
                                .resourcePath("res" + File.separator + "images" + File.separator + "card15.png")
                                .build();
            this.deck = new DeckImpl();
            this.deck.removeCard(this.deck.getCards().stream().findAny().get());
            this.deck.addCard(card1);
            assertTrue(this.deck.isCardInDeck(card1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void illegalCards() {
        assertThrows(NullPointerException.class, () -> new CardImpl.Builder().build());
        assertThrows(FileNotFoundException.class, () -> {            
            new CardImpl.Builder()
                        .attack(0)
                        .description("Desc")
                        .shield(0)
                        .name("prova")
                        .cost(2)
                        .resourcePath("")
                        .build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new CardImpl.Builder()
                        .attack(-2)
                        .description("Desc")
                        .shield(0)
                        .name("prova")
                        .cost(-3)
                        .resourcePath("res" + File.separator + "images" + File.separator + "card15.png")
                        .build();
        });
    }

    @Test
    public void illegalDeckCreation() {
        assertThrows(IOException.class, () -> this .deck = new DeckImpl(new FileReader("file inesistente")));
    }

    @Test
    public void testingEmptyDeck() {
        this.deck = new DeckImpl();
        while (!this.deck.getCards().isEmpty()) {
            this.deck.popCard();
        }
        assertEquals(Collections.EMPTY_LIST, this.deck.getCards());
        assertEquals(Optional.ofNullable(null), this.deck.getCard());
        assertEquals(Optional.ofNullable(null), this.deck.popCard());
    }
}

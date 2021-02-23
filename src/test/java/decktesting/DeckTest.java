package decktesting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.jupiter.api.Test;

import models.Card;
import models.CardImpl;
import models.Deck;
import models.DeckImpl;

class DeckTest {
    private Deck deck;
    private Card card;
    
    @Test
    public void testDeckCreation() {
        try {
            this.deck = new DeckImpl(new FileReader("res" + File.separator + "jsons" + File.separator + "DefaultDeck.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(this.deck.isDeckFull());
        
        while(!this.deck.getCards().isEmpty()) {
            this.deck.popCard();
        }
        assertFalse(this.deck.isDeckFull());
        
        this.deck = new DeckImpl();
        
        assertTrue(this.deck.isDeckFull());
        }
    
    @Test
    public void testAddCard() {
        Card card;
        try {
            card = new CardImpl.Builder()
                                    .name("Monster")
                                    .attack(1)
                                    .shield(2)
                                    .cost(2)
                                    .description("Description")
                                    .resourcePath("res" + File.separator + "images" + File.separator + "card15.png")
                                    .build();
            this.deck = new DeckImpl();
            this.deck.removeCard(this.deck.getCards().stream().findAny().get());
            this.deck.addCard(card);
            assertTrue(this.deck.isCardInDeck(card));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void illegalCards() {
        assertThrows(NullPointerException.class, () -> this.card = new CardImpl.Builder().build());
        assertThrows(FileNotFoundException.class, () -> {
            this.card = new CardImpl.Builder()
                                    .attack(0)
                                    .description("Desc")
                                    .shield(0)
                                    .name("prova")
                                    .cost(2)
                                    .resourcePath("")
                                    .build();
        });
        assertThrows(IllegalStateException.class, () -> {
            this.card = new CardImpl.Builder()
                                    .attack(-2)
                                    .description("Desc")
                                    .shield(0)
                                    .name("prova")
                                    .cost(-3)
                                    .resourcePath("res" + File.separator + "images" + File.separator + "card15.png")
                                    .build();
        });
    }
}

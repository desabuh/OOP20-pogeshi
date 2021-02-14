package battlefeaturetesting;

import org.junit.Test;

import models.*;

import static org.junit.Assert.*;

public class TestBattle {
    private Player p = new PlayerImp(new DeckImpl());
    private Enemy e = new EnemyImp(new DeckImpl());
    private Battle b;

    @Test
    public void testCards() {
        b = new BattleImpl(p, e);
        p.getDeck().addCard(new CardImpl(3, 0, 0, "Card name", "cardpath"));
        p.getDeck().addCard(new CardImpl(0, 2, 0, "Card name 2", "cardpath"));
        /**
         * The deck should have 2 cards
         * */
        assertEquals(2, p.getDeck().getCards().size());
        /**
         * Adding a card from deck to hand, should decrease the deck's size by 1
         * */
        p.getHand().addCard(p.getDeck().popCard().get());
        assertEquals(1, p.getDeck().getCards().size());
    }

    @Test
    public void testPlayerShield() {
        b = new BattleImpl(p, e);
        p.getDeck().addCard(new CardImpl(0, 2, 0, "Shield card", "cardpath")); // Adding a sample card in the player's deck that gives 2 shield
        p.getHand().addCard(p.getDeck().popCard().get()); // Adding the card to the player's hand
        b.playCard(0); // Playing the card
        assertEquals(2, p.getShield());
    }

}

package battlefeaturetesting;
import org.junit.Test;

import models.*;

import static org.junit.Assert.*;

public class TestBattle {
    /*private Player p = new PlayerImp(new DeckImpl());
    private EnemyImp e = new EnemyImp(new DeckImpl(), new Point2DImp(0, 0));*/
    private Battle b;

    /**
     * Testing the player's default deck's number of cards
     * */
    @Test
    public void testBasicDeck() {
        b = new BattleImpl();
        b.initializeCharacters();
        /**
         * The basic deck should have 10 cards
         * */
        assertEquals(10, b.getPlayer().getDeck().getCards().size());
        /**
         * Adding a card from deck to hand, should decrease the deck's size by 1
         * */
        b.getPlayer().getHand().addCard(b.getPlayer().getDeck().popCard().get());
        assertEquals(9, b.getPlayer().getDeck().getCards().size());
    }

    /**
     * Testing if the player can damage the enemy.
     * */
    @Test
    public void testPlayerDamaging() {
        b = new BattleImpl();
        b.initializeCharacters();
        int startingEnemyHealth = b.getEnemy().getHealth();
        int cardDamage = b.getPlayer().getHand().getCards().get(0).getAttack();
        b.playCard(0); // Playing the card
        assertEquals(startingEnemyHealth - cardDamage, b.getEnemy().getHealth());
    }

    /**
     * Tests if the battle can start without having initialized the characters who are going to battle.
     * It should throw an exception.
     * */
    @Test(expected = IllegalStateException.class)
    public void testNotInitializedBattle() {
        b = new BattleImpl();
        b.playCard(0);
    }

}

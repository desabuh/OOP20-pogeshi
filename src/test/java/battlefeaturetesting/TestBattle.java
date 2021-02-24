package battlefeaturetesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.battle.Battle;
import models.battle.BattleImpl;
import views.battle.BattleView;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

public class TestBattle {
    private Battle b;
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
    @Test
    public void testNotInitializedBattle() {
        b = new BattleImpl();
        assertThrows(IllegalStateException.class, () -> b.playCard(0));
    }

    /**
     * Testing the size of the player's hand.
     * */
    @Test
    public void testPlayerHand() {
        b = new BattleImpl();
        b.initializeCharacters();
        /**
         * The starting hand should have 3 cards
         * */
        assertEquals(models.battle.BattleImpl.BASE_STARTING_CARDS, b.getPlayer().getHand().getCards().size());
        /**
         * Ending the turn 10 times, therefore the player should draw 5 cards
         * */
        for (int i = 0; i < 10; i++) {
           b.endTurn();
        }
        /**
         * Even if the player drew 5 cards, for a total of 8, the hand's size should be capped at 5 cards
         * */
        assertEquals(models.battle.BattleImpl.MAX_CARDS_IN_HAND, b.getPlayer().getHand().getCards().size());
        /**
         * Playing a card should decrease the hand's size by 1
         * */
        b.playCard(0);
        assertEquals(models.battle.BattleImpl.MAX_CARDS_IN_HAND - 1, b.getPlayer().getHand().getCards().size());
    }

    /**
     * Test if the player can add shield to himself on card use.
     * */
    @Test
    public void testPlayerShield() {
        b = new BattleImpl();
        b.initializeCharacters();
        int cardShield = b.getPlayer().getHand().getCards().get(0).getShield(); // Getting the card's shield added
        b.playCard(0);
        assertEquals(cardShield, b.getPlayer().getShield());
    }

    /**
     * Test if the player can win.
     * */
    @Test
    public void testVictory() {
        b = new BattleImpl();
        b.initializeCharacters();
        while (!b.checkBattleEnd()) {
            b.playCard(0);
            b.endTurn();
            b.endTurn();
        }
        assertTrue(b.hasPlayerWon());
    }

   /* @Test
    public void testView() {
        Scene scene = SceneManager.of(LAYOUT.BATTLE).getScene();
        Stage stage = new Stage();
        stage.setScene(scene);
        FXMLLoader loader = SceneManager.of(LAYOUT.BATTLE).getLoader();
        Controller controller = loader.getController();
        BattleView view = (BattleView) controller.getView();
        b = new BattleImpl();
        b.initializeCharacters();
        view.updatePlayerStats(b.getPlayer().getHealth(), b.getPlayer().getShield());
        Label LBLPlayerHealth = (Label) scene.lookup("#LBLPlayerHealth");
        Label LBLPlayerShield = (Label) scene.lookup("#LBLPlayerShield");
        assertEquals(LBLPlayerHealth.getText(), String.valueOf(b.getPlayer().getHealth()));
        assertEquals(LBLPlayerShield.getText(), String.valueOf(b.getPlayer().getShield()));

    }*/

}

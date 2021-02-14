package controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.Battle;
import models.BattleImpl;
import models.Card;
import models.CardImpl;
import models.DeckImpl;
import models.Enemy;
import models.EnemyImp;
import models.Player;
import models.PlayerImp;

public final class BattleControllerImpl implements BattleController {
    /**
     * Time before hiding the "Not enough mana" label after displaying it.
     * */
    public static final int TIME_BEFORE_HIDING_MESSAGE = 3000;

    /*private Player p = new PlayerImpl(MAX_PLAYER_HEALTH);
    private Enemy e = new EnemyImpl(MAX_ENEMY_HEALTH);*/
    private Player p = new PlayerImp(new DeckImpl());
    private Enemy e = new EnemyImp(new DeckImpl());
    private Battle b = new BattleImpl();
    private int playerUnusedCombatMana = p.getMana();

    @FXML
    private Label LBLPlayerHealth;
    @FXML
    private Label LBLPlayerShield;
    @FXML
    private Label LBLPlayerDamage;
    @FXML
    private Label LBLEnemyHealth;
    @FXML
    private Label LBLEnemyShield;
    @FXML
    private HBox HBPlayerHand;
    @FXML
    private Label LBLEnemyDamage;
    @FXML
    private Label LBLAvailableMana;
    @FXML
    private Label LBLMaxMana;
    @FXML
    private Button BTNEndTurn;
    @FXML
    private Label LBLNoMana;

    @FXML
    public void initialize() {
        p.getHand().addCard(new CardImpl(3, 0, 1, "Carta prova", "ciao"));
        p.getHand().addCard(new CardImpl(3, 0, 10, "Carta prova costosa", "ciao"));
        LBLPlayerHealth.setText(String.valueOf(p.getHealt()));
        LBLAvailableMana.setText(String.valueOf(playerUnusedCombatMana));
        LBLMaxMana.setText(String.valueOf(p.getMana()));
        List<Card> hand = new ArrayList<>(p.getHand().getCards());
        for (int i = 0; i < hand.size(); i++) {
            final int inHand = i;
            Button b = new Button(hand.get(i).getName());
            b.setOnAction(new EventHandler<ActionEvent>() {
                private final int indexInHand = inHand;
                @Override
                public void handle(final ActionEvent event) {
                    selectedCard(indexInHand);
                }

            });
            HBPlayerHand.getChildren().add(b);
        }
        BTNEndTurn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                b.endTurn();
                if (b.currentTurn() instanceof Enemy) {
                    selectedCard(0);
                    BTNEndTurn.fire();
                } else {
                    p.setMana(p.getMana() + 1);
                    playerUnusedCombatMana = p.getMana();
                    LBLAvailableMana.setText(String.valueOf(playerUnusedCombatMana));
                    LBLMaxMana.setText(String.valueOf(p.getMana()));
                }
            }
        });
    }

    private void selectedCard(final int index) {
        Card selected;
        if (b.currentTurn() instanceof Player) {
            selected = p.getHand().getCards().get(index);
            /**
             * If the player has enough mana, the required mana for playing the card is spent, the various effects of the card
             * are applied (damage/add shield) and the card is removed
             * */
            if (b.isPlayable(selected, playerUnusedCombatMana)) {
                playerUnusedCombatMana -= selected.getCost();
                //e.damageEnemy(selected.getDamage());
                p.setShield(p.getShield() + selected.getDefense());
                p.getHand().getCards().remove(index);
                HBPlayerHand.getChildren().remove(index);
                updateHand(index);
                updateLabels(selected);
            } else {
                /**
                 * This is used to display the "Not enough mana!" message on the GUI for 3 seconds before automatically hiding it
                 * */
                new Thread() {
                    public void run() {
                        try {
                            LBLNoMana.setVisible(true);
                            Thread.sleep(TIME_BEFORE_HIDING_MESSAGE);
                            LBLNoMana.setVisible(false);
                        } catch (InterruptedException e) {
                            System.out.println("The thread died while sleeping");
                        } finally {
                            this.interrupt();
                        }
                    }
                }.start();
            }
        } else {
            /**
             * The enemy has no mana and a card can always be played
             * */
           /* selected = e.getHand().getCards().get(index);
            System.out.println("Enemy!");
            p.damagePlayer(selected.getDamage());
            e.addShield(selected.getShield());
            e.removeCard(index);
            updateLabels(selected);*/
        }
        b.checkBattleEnd();
    }

    /**
     * This is used instead of removing all the buttons and regenerating them.
     * Only their index is updated
     * */
    private void updateHand(final int startingIndex) {
        List<Card> hand = new ArrayList<>(p.getHand().getCards());
        for (int i = startingIndex; i < hand.size(); i++) {
            final int inHand = i;

            Button b = (Button) HBPlayerHand.getChildren().get(i);
            b.setOnAction(new EventHandler<ActionEvent>() {
                private final int indexInHand = inHand;
                @Override
                public void handle(final ActionEvent event) {
                    selectedCard(indexInHand);
                }

            });
        }
    }

    private void updateLabels(final Card c) {
        if (b.currentTurn() instanceof Player) {
            LBLEnemyDamage.setText("-" + String.valueOf(c.getAttack()));
            //LBLEnemyHealth.setText(String.valueOf(e.getHealth()));
            LBLAvailableMana.setText(String.valueOf(playerUnusedCombatMana));
            LBLEnemyDamage.setVisible(true);
            LBLPlayerDamage.setVisible(false);
        } else {
            LBLPlayerDamage.setText("-" + String.valueOf(c.getAttack()));
            LBLPlayerHealth.setText(String.valueOf(p.getHealt()));
            LBLPlayerShield.setText(String.valueOf(p.getShield()));
            //LBLEnemyShield.setText(String.valueOf(e.getShield()));
            LBLEnemyDamage.setVisible(false);
            LBLPlayerDamage.setVisible(true);
        }
    }

}

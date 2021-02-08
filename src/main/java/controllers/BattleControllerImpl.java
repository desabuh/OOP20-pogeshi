package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.Battle;
import models.BattleImpl;

public final class BattleControllerImpl implements BattleController {
    public static final int MAX_PLAYER_HEALTH = 30;
    public static final int MAX_ENEMY_HEALTH = 10;
    public static final int TIME_BEFORE_HIDING_MESSAGE = 3000;

    private Player p = new PlayerImpl(MAX_PLAYER_HEALTH);
    private Enemy e = new EnemyImpl(MAX_ENEMY_HEALTH);
    private Battle b = new BattleImpl();

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
        p.addCard(new CardImpl("Carta prova", 7, 3, 0));
        p.addCard(new CardImpl("Carta prova 2", 1, 1, 0));
        e.addCard(new CardImpl("Carta nemico", 0, 1, 0));
        e.addCard(new CardImpl("Carta nemico 2", 0, 0, 2));
        LBLPlayerHealth.setText(String.valueOf(p.getHealth()));
        List<Card> hand = new ArrayList<>(p.getHand());
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
                    p.setUnusedCombatMana(p.getMana());
                    LBLAvailableMana.setText(String.valueOf(p.getUnusedCombatMana()));
                    LBLMaxMana.setText(String.valueOf(p.getMana()));
                }
            }
        });
    }

    private void selectedCard(final int index) {
        Card selected;
        if (b.currentTurn() instanceof Player) {
            selected = p.getHand().get(index);
            /**
             * If the player has enough mana, the required mana for playing the card is spent, the various effects of the card
             * are applied (damage/add shield) and the card is removed
             * */
            if (b.isPlayable(selected, p.getUnusedCombatMana())) {
                System.out.println("Player!");
                p.setUnusedCombatMana(p.getUnusedCombatMana() - selected.getCost());
                e.damageEnemy(selected.getDamage());
                p.addShield(selected.getShield());
                p.removeCard(index);
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
            selected = e.getHand().get(index);
            System.out.println("Enemy!");
            p.damagePlayer(selected.getDamage());
            e.addShield(selected.getShield());
            e.removeCard(index);
            updateLabels(selected);
        }
        b.checkBattleEnd();
    }

    /**
     * This is used instead of removing all the buttons and regenerating them.
     * Only their index is updated
     * */
    private void updateHand(final int startingIndex) {
        List<Card> hand = new ArrayList<>(p.getHand());
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
            LBLEnemyDamage.setText("-" + String.valueOf(c.getDamage()));
            LBLEnemyHealth.setText(String.valueOf(e.getHealth()));
            LBLAvailableMana.setText(String.valueOf(p.getUnusedCombatMana()));
            LBLEnemyDamage.setVisible(true);
            LBLPlayerDamage.setVisible(false);
        } else {
            LBLPlayerDamage.setText("-" + String.valueOf(c.getDamage()));
            LBLPlayerHealth.setText(String.valueOf(p.getHealth()));
            LBLPlayerShield.setText(String.valueOf(p.getShield()));
            LBLEnemyShield.setText(String.valueOf(e.getShield()));
            LBLEnemyDamage.setVisible(false);
            LBLPlayerDamage.setVisible(true);
        }
    }

}

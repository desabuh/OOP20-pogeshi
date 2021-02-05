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

public class BattleControllerImpl implements BattleController {
    public static final int MAX_PLAYER_HEALTH = 30;
    public static final int MAX_ENEMY_HEALTH = 10;

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
        if (b.currentTurn() instanceof Player) {
            if (b.isPlayable(p.getHand().get(index), p.getUnusedCombatMana())) {
                System.out.println("Player!");
                p.setUnusedCombatMana(p.getUnusedCombatMana() - p.getHand().get(index).getCost());
                LBLEnemyDamage.setText("-" + String.valueOf(p.getHand().get(index).getDamage()));
                e.damageEnemy(p.getHand().get(index).getDamage());
                p.addShield(p.getHand().get(index).getShield());
                LBLEnemyHealth.setText(String.valueOf(e.getHealth()));
                p.removeCard(index);
                HBPlayerHand.getChildren().remove(index);
                updateHand(index);
                LBLEnemyDamage.setVisible(true);
                LBLPlayerDamage.setVisible(false);
                LBLAvailableMana.setText(String.valueOf(p.getUnusedCombatMana()));
            } else {
                System.out.println("Not enough mana!");
            }
        } else {
            System.out.println("Enemy!");
            LBLPlayerDamage.setText("-" + String.valueOf(e.getHand().get(index).getDamage()));
            p.damagePlayer(e.getHand().get(index).getDamage());
            e.addShield(e.getHand().get(index).getShield());
            e.removeCard(index);
            LBLPlayerHealth.setText(String.valueOf(p.getHealth()));
            LBLPlayerShield.setText(String.valueOf(p.getShield()));
            LBLEnemyShield.setText(String.valueOf(e.getShield()));
            LBLEnemyDamage.setVisible(false);
            LBLPlayerDamage.setVisible(true);
        }
        b.checkBattleEnd();
    }

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

}

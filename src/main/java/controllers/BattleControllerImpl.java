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
    private HBox HBPlayerHand;
    @FXML
    private Label LBLEnemyDamage;
    @FXML
    private Label LBLAvailableMana;
    @FXML
    private Button BTNEndTurn;

    @FXML
    public void initialize() {
        p.addCard(new CardImpl("Carta prova", 2, 3, 0));
        p.addCard(new CardImpl("Carta prova 2", 1, 1, 0));
        e.addCard(new CardImpl("Carta nemico", 1, 1, 0));
        e.addCard(new CardImpl("Carta nemico 2", 2, 0, 2));
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
                /*Reset mana giocatore?*/
            }
        });
    }

    private void selectedCard(final int index) {
        Optional<? extends Character> turn = b.playCard(p.getHand().get(index), p.getMana());
        if (turn.isPresent()) {
            if (turn.get() instanceof Player) {
                System.out.println("Player!");
                p.setUnusedCombatMana(p.getUnusedCombatMana() - p.getHand().get(index).getCost());
                LBLEnemyDamage.setText("-" + String.valueOf(p.getHand().get(index).getDamage()));
                p.removeCard(index);
                HBPlayerHand.getChildren().remove(index);
                updateHand(index);
                LBLEnemyDamage.setVisible(true);
                LBLAvailableMana.setText(String.valueOf(p.getUnusedCombatMana()));
                b.checkBattleEnd();
            } else {
                System.out.println("Enemy!");
            }
        } else {
            System.out.println("Not enough mana!");
        }
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

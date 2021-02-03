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

public class BattleControllerImpl implements BattleController {
    public static final int MAX_PLAYER_HEALTH = 30;

    private Player p = new PlayerImpl(MAX_PLAYER_HEALTH);
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
    public void initialize() {
        p.addCard(new CardImpl("Carta prova", 2, 3, 0));
        p.addCard(new CardImpl("Carta prova 2", 1, 1, 0));
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
    }

    private void selectedCard(final int index) {
        if (b.playCard(p.getHand().get(index), p)) {
            LBLEnemyDamage.setText("-" + String.valueOf(p.getHand().get(index).getDamage()));
            p.removeCard(index);
            HBPlayerHand.getChildren().remove(index);
            updateHand(index);
            LBLEnemyDamage.setVisible(true);
            LBLAvailableMana.setText(String.valueOf(p.getUnusedCombatMana()));
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

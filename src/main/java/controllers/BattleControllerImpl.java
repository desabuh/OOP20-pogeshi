package controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.Battle;
import models.BattleImpl;
import models.Card;
import models.Enemy;
import models.Player;
import views.BattleView;
import views.View;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

public final class BattleControllerImpl implements BattleController {

    private Battle b = new BattleImpl();
    private BattleView battleView;

    @Inject
    public BattleControllerImpl(final Battle battle, final BattleView battleView) {
        this.b = battle;
        this.battleView = battleView;
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
                this.battleView.setScene(SceneManager.of(LAYOUT.BATTLE).getScene());
                battleView.initializeParams();
                List<Card> hand = new ArrayList<>(b.getPlayer().getHand().getCards());
                for (int i = 0; i < hand.size(); i++) {
                    attachSelectEvent(hand.get(i), i);
                }
                battleView.updatePlayerStats(b.getPlayer().getHealth(), b.getPlayer().getShield());
                battleView.updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
                /**
                 * This is implemented to give the view the function to execute when the player wants to end the turn.
                 * This allows to change the event's behaviour without having to change anything into the view, as long
                 * as the element that ends the turn is clickable.
                 * */
                battleView.setEndTurnEvent(new EventHandler<>() {

                    @Override
                    public void handle(final ActionEvent event) {
                        final int nPlayerCards = b.getPlayer().getHand().getCards().size();
                        b.endTurn();
                        if (b.currentTurn() instanceof Enemy) {
                            selectedCard(0);
                            this.handle(event);
                        } else {
                            battleView.updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
                            if (b.getPlayer().getHand().getCards().size() != nPlayerCards) {
                                addLatestCardToHand();
                            }
                        }
                    }
                });
            });

        b.initializeCharacters();

    }

    private void selectedCard(final int index) {
        Card selected;
        if (b.currentTurn() instanceof Player) {
            selected = b.getPlayer().getHand().getCards().get(index);
            /**
             * If the player has enough mana, the required mana for playing the card is spent, the various effects of the card
             * are applied (damage/add shield) and the card is removed
             * */
            if (b.playCard(index)) {
                battleView.resetHand();
                updateHand(0);
                updateViewLabels(selected);
            } else {
                battleView.displayNotEnoughMana();
            }
        } else {
            /**
             * The enemy has no mana and a card can always be played.
             * A card will always be returned.
             * */
            selected = Iterables.getFirst(b.getEnemy().getDeck().getCards(), null);
            b.playCard(index);
            updateViewLabels(selected);
        }
        if (b.checkBattleEnd()) {
            Alert battleFinish = new Alert(AlertType.INFORMATION);
            if (b.currentTurn() instanceof Player) {
                battleFinish.setTitle("Victory!");
                battleFinish.setContentText("You won the fight!");
            } else {
                battleFinish.setTitle("Defeat!");
                battleFinish.setContentText("You died!");
            }
            battleFinish.showAndWait();
            System.exit(0);
        }
    }

    /**
     * This is used instead of removing all the images and regenerating them.
     * */
    private void updateHand(final int startingIndex) {
        List<Card> hand = new ArrayList<>(b.getPlayer().getHand().getCards());
        for (int i = startingIndex; i < hand.size(); i++) {
            System.out.println(startingIndex);
            attachSelectEvent(hand.get(i), i);
        }
    }

    private void updateViewLabels(final Card c) {
        if (b.currentTurn() instanceof Player) {
            battleView.showDamageToEnemy(c.getAttack());
            battleView.updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
        } else {
            battleView.showDamageToPlayer(c.getAttack());
        }
        battleView.updateEnemyStats(b.getEnemy().getHealth(), b.getEnemy().getShield());
        battleView.updatePlayerStats(b.getPlayer().getHealth(), b.getPlayer().getShield());
    }

    private void attachSelectEvent(final Card c, final int i) {

        Text description = new Text(
                "Card name: " + c.getName() + "\n"
                + "Mana cost: " + c.getCost() + "\n"
                + "Damage: " + String.valueOf(c.getAttack()) + "\n"
                + "Shield added: " + String.valueOf(c.getShield()) + "\n\n"
                + "Description: " + c.getDescription()
                );

        battleView.addCardToHand(c.getResourcePath(), description, new EventHandler<>() {

            @Override
            public void handle(final MouseEvent event) {
                selectedCard(i);
            }
        });

    }
    /**
     * Used to add the latest drawn card to the view.
     * */
    private void addLatestCardToHand() {
        int handSize = b.getPlayer().getHand().getCards().size();
        Card latest = Iterables.getLast(b.getPlayer().getHand().getCards()); // Found on StackOverflow, a more elegant way to get the latest card instead of size() - 1
        attachSelectEvent(latest, handSize - 1);
    }

    @Override
    public View getView() {
        return this.battleView;
    }

    @Override
    public void callBackAction(final Object data) {
        if (data instanceof Player) {
            b.setPlayer((Player) data);
        }
        // TODO Auto-generated method stub
    }

    private boolean battleFinish() {
        return true;
    }

}

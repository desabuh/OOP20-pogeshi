package controllers.battle;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Suppliers;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import controllers.maincontroller.SwitchControllerRequest;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.Character.Enemy;
import models.Character.Player;
import models.battle.Battle;
import models.battle.BattleImpl;
import models.deck.card.Card;
import notifier.EventBus;
import views.View;
import views.battle.BattleView;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

public final class BattleControllerImpl implements BattleController {

    private Battle battle = new BattleImpl();
    private BattleView battleView;
    private EventBus<Request<LAYOUT, ? extends Object>> notifier;
    private MainController mainController;

    @Inject
    public BattleControllerImpl(final Battle battle, final BattleView battleView, final MainController mainController, final EventBus<Request<LAYOUT, ? extends Object>> notifier) {
        this.battle = battle;
        this.battleView = battleView;
        this.notifier = notifier;
        this.mainController = mainController;
        this.notifier.register(mainController);
    }

    @FXML
    public void initialize() {
        /**
         * Everything inside this function is to be performed when the view's stage is set.
         * If it were to execute without runLater, the view would try to access a stage that has not been loaded yet,
         * and it would inevitably crash.
         * */
        Platform.runLater(() -> {
                this.battleView.setScene(SceneManager.of(LAYOUT.BATTLE).getScene());
                battleView.initializeParams();
                List<Card> hand = new ArrayList<>(battle.getPlayer().getHand().getCards());
                for (int i = 0; i < hand.size(); i++) {
                    attachSelectEvent(hand.get(i), i);
                }
                battleView.updatePlayerStats(battle.getPlayer().getHealth(), battle.getPlayer().getShield());
                battleView.updateEnemyStats(battle.getEnemy().getHealth(), battle.getEnemy().getShield());
                battleView.updateManaLabel(battle.getPlayerUnusedCombatMana(), battle.getPlayer().getMana());
                /**
                 * This is implemented to give the view the function to execute when the player wants to end the turn.
                 * This allows to change the event's behaviour without having to change anything into the view, as long
                 * as the element that ends the turn is clickable.
                 * This is the most generic solution I've thought of.
                 * */
                battleView.setEndTurnEvent(new EventHandler<>() {

                    @Override
                    public void handle(final ActionEvent event) {
                        final int nPlayerCards = battle.getPlayer().getHand().getCards().size();
                        battle.endTurn();
                        if (battle.currentTurn() instanceof Enemy) {
                            selectedCard(0);
                            this.handle(event);
                        } else {
                            battleView.updateManaLabel(battle.getPlayerUnusedCombatMana(), battle.getPlayer().getMana());
                            if (battle.getPlayer().getHand().getCards().size() != nPlayerCards) {
                                addLatestCardToHand();
                            }
                        }
                    }
                });
            });

        battle.initializeCharacters();

    }

    private void selectedCard(final int index) {
        Card selected;
        if (battle.currentTurn() instanceof Player) {
            selected = battle.getPlayer().getHand().getCards().get(index);
            /**
             * If the player has enough mana, the required mana for playing the card is spent, the various effects of the card
             * are applied (damage/add shield) and the card is removed
             * */
            if (battle.playCard(index)) {
                battleView.resetHand();
                updateHand(0);
                updateViewLabels(selected);
            } else {
                battleView.displayNotEnoughMana();
            }
        } else {
            /**
             * The enemy has no mana and a card can always be played.
             * */
            selected = Iterables.getFirst(battle.getEnemy().getDeck().getCards(), null);
            battle.playCard(index);
            updateViewLabels(selected);
        }
        if (battle.checkBattleEnd()) {
            this.battleFinish();
            /*if (battle.currentTurn() instanceof Player) {
                battleView.showMessage("Victory!", "You won the fight!");
            } else {
                battleView.showMessage("Defeat!", "You died!");
            }
            System.exit(0);*/
        }
    }

    /**
     * Regenerates all the cards from the starting index.
     * */
    private void updateHand(final int startingIndex) {
        List<Card> hand = new ArrayList<>(battle.getPlayer().getHand().getCards());
        for (int i = startingIndex; i < hand.size(); i++) {
            System.out.println(startingIndex);
            attachSelectEvent(hand.get(i), i);
        }
    }

    /**
     * Tells the view to update the corresponding labels when a card is played, based on the turn the card is played.
     * */
    private void updateViewLabels(final Card c) {
        if (battle.currentTurn() instanceof Player) {
            battleView.showDamageToEnemy(c.getAttack());
            battleView.updateManaLabel(battle.getPlayerUnusedCombatMana(), battle.getPlayer().getMana());
        } else {
            battleView.showDamageToPlayer(c.getAttack());
        }
        battleView.updateEnemyStats(battle.getEnemy().getHealth(), battle.getEnemy().getShield());
        battleView.updatePlayerStats(battle.getPlayer().getHealth(), battle.getPlayer().getShield());
    }

    /**
     * This is the main functions that defines what a card should do when a specific event happens (in this case, when its
     * image on the view is clicked, but this is a generic event that can be bound to anything).
     * */
    private void attachSelectEvent(final Card c, final int i) {

        Text description = new Text(
                "Card name: " + c.getName() + "\n"
                + "Mana cost: " + c.getCost() + "\n"
                + "Damage: " + String.valueOf(c.getAttack()) + "\n"
                + "Shield added: " + String.valueOf(c.getShield()) + "\n\n"
                + "Description: " + c.getDescription()
                );
        /**
         * Here a generic eventHandler is passed, and the battleView can handle it however it pleases.
         * */
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
        int handSize = battle.getPlayer().getHand().getCards().size();
        Card latest = Iterables.getLast(battle.getPlayer().getHand().getCards());
        attachSelectEvent(latest, handSize - 1);
    }

    /**
     * @return The battle's view currently instantiated
     * */
    @Override
    public View getView() {
        return this.battleView;
    }

    /**
     * This is used to let the main controller set the battle's player in another moment that's not the instantiation of the battle.
     * */
    @Override
    public void callBackAction(final Object data) {
        if (data instanceof Player) {
            battle.setPlayer((Player) data);
        }
    }

    /**
     * Function to call when the battle is finished. Switches the program's flow to the appropriate controller, based on the battle's result.
     * */
    private void battleFinish() {
        if (battle.hasPlayerWon()) {
            this.notifier
                .notifyListener(new SwitchControllerRequest<LAYOUT, Player>(LAYOUT.WORLDMAP, Suppliers.ofInstance(this.battle.getPlayer())));
        } else {
            this.notifier
                .notifyListener(new SwitchControllerRequest<LAYOUT, Boolean>(LAYOUT.ACCOUNT, Suppliers.ofInstance(false)));
        }
    }

}

package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    /**
     * The number of cards the player starts a new battle with.
     * */
    public static final int BASE_STARTING_CARDS = 3;
    /**
     * How wide the cards currently in the hand is displayed.
     * */
    public static final int CARD_WIDTH = 150;

    /*private Player p = new PlayerImpl(MAX_PLAYER_HEALTH);
    private Enemy e = new EnemyImpl(MAX_ENEMY_HEALTH);*/
    //private Player p = new PlayerImp(new DeckImpl());
    //private Enemy e = new EnemyImp(new DeckImpl());
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
    private Button BTNEndTurn;
    @FXML
    private Label LBLNoMana;
    @FXML
    private Label LBLMana;
    @FXML
    private ImageView IMGPlayer;
    @FXML
    private ImageView IMGEnemy;

    @FXML
    public void initialize() {
        b.initializeCharacters();
        IMGPlayer.setImage(new Image(new File("res" + File.separator + "images" + File.separator + "PlayerImage.png").toURI().toString()));
        IMGEnemy.setImage(new Image(new File("res" + File.separator + "images" + File.separator + "EnemyImage.png").toURI().toString()));
        LBLPlayerHealth.setText(String.valueOf(b.getPlayer().getHealt()));
        updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
        List<Card> hand = new ArrayList<>(b.getPlayer().getHand().getCards());
        for (int i = 0; i < hand.size(); i++) {
            ImageView card = new ImageView();
            card.setImage(new Image(new File(hand.get(i).getResourcePath()).toURI().toString()));
            card.setFitWidth(CARD_WIDTH);
            card.setPreserveRatio(true);
            attachSelectEvent(card, i);
            HBPlayerHand.getChildren().add(card);
        }
        BTNEndTurn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                b.endTurn();
                if (b.currentTurn() instanceof Enemy) {
                    selectedCard(0);
                    BTNEndTurn.fire();
                } else {
                    updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
                }
            }
        });
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
            //b.playCard(0);
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
     * Only their index is updated
     * */
    private void updateHand(final int startingIndex) {
        List<Card> hand = new ArrayList<>(b.getPlayer().getHand().getCards());
        for (int i = startingIndex; i < hand.size(); i++) {
            System.out.println(startingIndex);
            attachSelectEvent((ImageView) HBPlayerHand.getChildren().get(i), i);
        }
    }

    private void updateLabels(final Card c) {
        if (b.currentTurn() instanceof Player) {
            LBLEnemyDamage.setText("-" + String.valueOf(c.getAttack()));
            //LBLEnemyHealth.setText(String.valueOf(e.getHealth()));
            updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
            LBLEnemyDamage.setVisible(true);
            LBLPlayerDamage.setVisible(false);
        } else {
            LBLPlayerDamage.setText("-" + String.valueOf(c.getAttack()));
            LBLPlayerHealth.setText(String.valueOf(b.getPlayer().getHealt()));
            LBLPlayerShield.setText(String.valueOf(b.getPlayer().getShield()));
            //LBLEnemyShield.setText(String.valueOf(e.getShield()));
            LBLEnemyDamage.setVisible(false);
            LBLPlayerDamage.setVisible(true);
        }
    }

    private void updateManaLabel(final int unspent, final int max) {
        LBLMana.setText("Mana: " + String.valueOf(unspent) + " / " + String.valueOf(max));
    }

    private void attachSelectEvent(final ImageView img,  final int i) {
        img.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                // TODO Auto-generated method stub
                selectedCard(i);
            }

        });

    }

}

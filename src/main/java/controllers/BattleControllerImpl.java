package controllers;

import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import models.Battle;
import models.BattleImpl;
import models.Card;
import models.CardImpl;
import models.DeckImpl;
import models.Enemy;
import models.EnemyImp;
import models.Player;
import models.PlayerImp;
import views.BattleView;
import views.View;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

public final class BattleControllerImpl implements BattleController {
    /**
     * Time before hiding the "Not enough mana" label after displaying it.
     * */
    public static final int TIME_BEFORE_HIDING_MESSAGE = 3000;
    /**
     * How wide the cards currently in the hand is displayed.
     * */
    public static final int CARD_WIDTH = 150;

    private Battle b = new BattleImpl();
    private BattleView battleView;

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
    private TextFlow txtCardInfo;

    @Inject
    public BattleControllerImpl(Battle battle, BattleView battleView) {
        this.b = battle;
        this.battleView = battleView;
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
                this.battleView.setScene(SceneManager.of(LAYOUT.BATTLE).getScene());
                battleView.initializeParams();
            });
        b.initializeCharacters();
        IMGPlayer.setImage(new Image(new File("res" + File.separator + "images" + File.separator + "PlayerImage.png").toURI().toString()));
        IMGEnemy.setImage(new Image(new File("res" + File.separator + "images" + File.separator + "EnemyImage.png").toURI().toString()));
        LBLPlayerHealth.setText(String.valueOf(b.getPlayer().getHealth()));
        updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
        List<Card> hand = new ArrayList<>(b.getPlayer().getHand().getCards());
        for (int i = 0; i < hand.size(); i++) {
            ImageView card = new ImageView();
            card.setImage(new Image(new File(hand.get(i).getResourcePath()).toURI().toString()));
            card.setFitWidth(CARD_WIDTH);
            card.setPreserveRatio(true);
            attachSelectEvent(hand.get(i), card, i);
            HBPlayerHand.getChildren().add(card);
        }
        
        
        //regenerateHand();
        BTNEndTurn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(final ActionEvent event) {
                battleView.updatePlayerStats(3, 0);
                b.endTurn();
                if (b.currentTurn() instanceof Enemy) {
                    selectedCard(0);
                    BTNEndTurn.fire();
                } else {
                    updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
                    if (HBPlayerHand.getChildren().size() != b.getPlayer().getHand().getCards().size()) {
                        addLatestCardToHand();
                    }
                }
            }
        });
    }
    
    private void initializeScene() {
        /*IMGPlayer.setImage(new Image(new File("res" + File.separator + "images" + File.separator + "PlayerImage.png").toURI().toString()));
        IMGEnemy.setImage(new Image(new File("res" + File.separator + "images" + File.separator + "EnemyImage.png").toURI().toString()));
        LBLPlayerHealth.setText(String.valueOf(b.getPlayer().getHealth()));
        updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());*/
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
            selected = Iterables.getFirst(b.getEnemy().getDeck().getCards(), null);
            b.playCard(index);
            updateLabels(selected);
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
            attachSelectEvent(hand.get(i), (ImageView) HBPlayerHand.getChildren().get(i), i);
        }
    }

    private void updateLabels(final Card c) {
        if (b.currentTurn() instanceof Player) {
            LBLEnemyDamage.setText("-" + String.valueOf(c.getAttack()));
            LBLEnemyHealth.setText(String.valueOf(b.getEnemy().getHealth()));
            LBLEnemyDamage.setVisible(true);
            LBLPlayerDamage.setVisible(false);
            updateManaLabel(b.getPlayerUnusedCombatMana(), b.getPlayer().getMana());
        } else {
            LBLPlayerDamage.setText("-" + String.valueOf(c.getAttack()));
            LBLPlayerHealth.setText(String.valueOf(b.getPlayer().getHealth()));
            LBLEnemyDamage.setVisible(false);
            LBLPlayerDamage.setVisible(true);
        }
        LBLEnemyShield.setText(String.valueOf(b.getEnemy().getShield()));
        LBLPlayerShield.setText(String.valueOf(b.getPlayer().getShield()));

    }

    private void updateManaLabel(final int unspent, final int max) {
        LBLMana.setText("Mana: " + String.valueOf(unspent) + " / " + String.valueOf(max));
    }

    private void attachSelectEvent(final Card c, final ImageView img,  final int i) {
        img.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                selectedCard(i);
            }

        });
        /**
         * Shows the card's properties on the right side of the hand.
         * */
        img.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                Text cardDescription = new Text(
                        "Card name: " + c.getName() + "\n"
                        + "Mana cost: " + c.getCost() + "\n"
                        + "Damage: " + String.valueOf(c.getAttack()) + "\n"
                        + "Shield added: " + String.valueOf(c.getShield()) + "\n\n"
                        + "Description: " + c.getDescription()
                        );
                cardDescription.setStyle("-fx-font: 18 arial;");
                txtCardInfo.getChildren().add(cardDescription);
            }
        });

        /**
         * Hides the card's properties.
         * */
        img.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                final int totalTexts = txtCardInfo.getChildren().size();
                for (int i = 0; i < totalTexts; i++) {
                    txtCardInfo.getChildren().remove(0);
                }
            }
        });

    }
    /**
     * Used to add the latest drawn card to the view.
     * */
    private void addLatestCardToHand() {
        Card latest = Iterables.getLast(b.getPlayer().getHand().getCards()); // Found on StackOverflow, a more elegant way to get the latest card instead of size() - 1
        ImageView card = new ImageView();
        card.setImage(new Image(new File(latest.getResourcePath()).toURI().toString()));
        card.setFitWidth(CARD_WIDTH);
        card.setPreserveRatio(true);
        attachSelectEvent(latest, card, HBPlayerHand.getChildren().size());
        HBPlayerHand.getChildren().add(card);
    }

    @Override
    public View getView() {
        return this.battleView;
    }

    @Override
    public void callBackAction(final Object data) {
        if (data instanceof Player) {
            //b.setPlayer(data);
        }
        // TODO Auto-generated method stub
    }

    private boolean battleFinish() {
        return true;
    }

    /*private void regenerateHand() {
        for (int i = 0; i < HBPlayerHand.getChildren().size(); i++) {
            HBPlayerHand.getChildren().remove(0);
        }
        List<Card> hand = new ArrayList<>(b.getPlayer().getHand().getCards());
        for (int i = 0; i < hand.size(); i++) {
            ImageView card = new ImageView();
            card.setImage(new Image(new File(hand.get(i).getResourcePath()).toURI().toString()));
            card.setFitWidth(CARD_WIDTH);
            card.setPreserveRatio(true);
            attachSelectEvent(card, i);
            HBPlayerHand.getChildren().add(card);
        }
    }*/

}

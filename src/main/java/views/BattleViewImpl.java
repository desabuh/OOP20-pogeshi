package views;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import views.render.Render;
import views.scene.layout.LAYOUT;

public final class BattleViewImpl extends JavafxView implements BattleView {

    /**
     * How wide the cards currently in the hand are displayed.
     * */
    public static final int CARD_WIDTH = 150;

    /**
     * Time before hiding the "Not enough mana" label after displaying it.
     * */
    public static final int TIME_BEFORE_HIDING_MESSAGE = 3000;


    /**
     * The scene layout of the battle.
     * */
    public static final LAYOUT ACTUAL_LAYOUT = LAYOUT.BATTLE;


    @FXML
    private HBox HBPlayerHand;
    @FXML
    private Label LBLEnemyDamage;
    @FXML
    private Label LBLPlayerDamage;
    @FXML
    private Label LBLMana;
    @FXML
    private Label LBLEnemyHealth;
    @FXML
    private Label LBLEnemyShield;
    @FXML
    private Label LBLPlayerHealth;
    @FXML
    private Label LBLPlayerShield;
    @FXML
    private ImageView IMGPlayer;
    @FXML
    private ImageView IMGEnemy;
    @FXML
    private Label LBLNoMana;
    @FXML
    private Button BTNEndTurn;
    @FXML
    private TextFlow txtCardInfo;

    /**
     * Sets the current BattleView's stage and layout.
     * @param stage The stage to load the battle into.
     * */
    public BattleViewImpl(final Stage stage) {
        super(stage, LAYOUT.BATTLE);
    }

    public void initializeParams() {
        Scene scene = this.getScene();
        System.out.println(scene);
        LBLMana = (Label) scene.lookup("#LBLMana");
        LBLEnemyDamage = (Label) scene.lookup("#LBLEnemyDamage");
        LBLPlayerDamage = (Label) scene.lookup("#LBLPlayerDamage");
        LBLEnemyHealth = (Label) scene.lookup("#LBLEnemyHealth");
        LBLPlayerHealth = (Label) scene.lookup("#LBLPlayerHealth");
        LBLPlayerShield = (Label) scene.lookup("#LBLPlayerShield");
        LBLEnemyShield = (Label) scene.lookup("#LBLEnemyShield");
        txtCardInfo = (TextFlow) scene.lookup("#txtCardInfo");
        HBPlayerHand = (HBox) scene.lookup("#HBPlayerHand");
        LBLNoMana = (Label) scene.lookup("#LBLNoMana");
        BTNEndTurn = (Button) scene.lookup("#BTNEndTurn");
        IMGPlayer = (ImageView) scene.lookup("#IMGPlayer");
        IMGEnemy = (ImageView) scene.lookup("#IMGEnemy");
        IMGPlayer.setImage(new Image(new File("res" + File.separator + "images" + File.separator + "PlayerImage.png").toURI().toString()));
        IMGEnemy.setImage(new Image(new File("res" + File.separator + "images" + File.separator + "EnemyImage.png").toURI().toString()));

    }

    @Override
    public void updateManaLabel(final int unspent, final int max) {
        LBLMana.setText("Mana: " + String.valueOf(unspent) + " / " + String.valueOf(max));
    }

    @Override
    public void updateEnemyStats(final int health, final int shield) {
        LBLEnemyHealth.setText(String.valueOf(health));
        LBLEnemyShield.setText(String.valueOf(shield));
    }

    @Override
    public void updatePlayerStats(final int health, final int shield) {
        LBLPlayerHealth.setText(String.valueOf(health));
        LBLPlayerShield.setText(String.valueOf(shield));
    }

    public void addCardToHand(final String imagePath, final Text description, final EventHandler<MouseEvent> onClick) {
        ImageView card = new ImageView();
        card.setImage(new Image(new File(imagePath).toURI().toString()));
        card.setFitWidth(CARD_WIDTH);
        card.setPreserveRatio(true);

        /**
         * Sets a generic event to execute when the card is clicked.
         * */
        card.setOnMouseClicked(onClick);

        /**
         * Shows the card's properties on the right side of the hand.
         * */
        card.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                Text cardDescription = description;
                cardDescription.setStyle("-fx-font: 18 arial;");
                txtCardInfo.getChildren().add(cardDescription);
            }
        });

        /**
         * Hides the card's properties.
         * */
        card.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                final int totalTexts = txtCardInfo.getChildren().size();
                for (int i = 0; i < totalTexts; i++) {
                    txtCardInfo.getChildren().remove(0);
                }
            }
        });


        HBPlayerHand.getChildren().add(card);
    }

    public void resetHand() {
        int nCards = HBPlayerHand.getChildren().size();
        for (int i = 0; i < nCards; i++) {
            HBPlayerHand.getChildren().remove(0);
        }
    }


    @Override
    public void updateEntity(final Render render, final Point2D x, final Point2D y) {
        // TODO Auto-generated method stub
    }

    public void showDamageToEnemy(final int amount) {
        LBLEnemyDamage.setText("-" + String.valueOf(amount));
        LBLEnemyDamage.setVisible(true);
        LBLPlayerDamage.setVisible(false);
    }

    public void showDamageToPlayer(final int amount) {
        LBLPlayerDamage.setText("-" + String.valueOf(amount));
        LBLPlayerDamage.setVisible(true);
        LBLEnemyDamage.setVisible(false);
    }

    public void displayNotEnoughMana() {
        /**
         * This is used to display the "Not enough mana!" message on the GUI for TIME_BEFORE_HIDING_MESSAGE milliseconds
         *  before automatically hiding it
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

    public void setEndTurnEvent(final EventHandler<ActionEvent> buttonClicked) {
        BTNEndTurn.setOnAction(buttonClicked);
    }


}

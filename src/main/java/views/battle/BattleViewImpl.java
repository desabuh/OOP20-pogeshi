package views.battle;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import views.JavafxView;
import views.drawer.CanvasDrawer;
import views.drawer.Drawer;
import views.render.Render;
import views.scene.layout.LAYOUT;
import models.GameMap.Point2D;
import models.GameMap.Point2DImp;
import views.render.RenderFactory;
import views.render.BattleRenderFactoryImpl;
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

    private RenderFactory renderFactory = new BattleRenderFactoryImpl();
    private Drawer<Canvas> playerDrawer;
    private Drawer<Canvas> enemyDrawer;

    @FXML
    private HBox hbPlayerHand;
    @FXML
    private Label lblEnemyDamage;
    @FXML
    private Label lblPlayerDamage;
    @FXML
    private Label lblMana;
    @FXML
    private Label lblEnemyHealth;
    @FXML
    private Label lblEnemyShield;
    @FXML
    private Label lblPlayerHealth;
    @FXML
    private Label lblPlayerShield;
    @FXML
    private Label lblNoMana;
    @FXML
    private Button btnEndTurn;
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
        lblMana = (Label) scene.lookup("#lblMana");
        lblEnemyDamage = (Label) scene.lookup("#lblEnemyDamage");
        lblPlayerDamage = (Label) scene.lookup("#lblPlayerDamage");
        lblEnemyHealth = (Label) scene.lookup("#lblEnemyHealth");
        lblPlayerHealth = (Label) scene.lookup("#lblPlayerHealth");
        lblPlayerShield = (Label) scene.lookup("#lblPlayerShield");
        lblEnemyShield = (Label) scene.lookup("#lblEnemyShield");
        txtCardInfo = (TextFlow) scene.lookup("#txtCardInfo");
        hbPlayerHand = (HBox) scene.lookup("#hbPlayerHand");
        lblNoMana = (Label) scene.lookup("#lblNoMana");
        btnEndTurn = (Button) scene.lookup("#btnEndTurn");
        playerDrawer = new CanvasDrawer((Canvas) scene.lookup("#cvPlayer"));
        playerDrawer.draw(renderFactory.renderPlayer(), new Point2DImp(0, 0), new Point2DImp(0, 0));
        enemyDrawer = new CanvasDrawer((Canvas) scene.lookup("#cvEnemy"));
        enemyDrawer.draw(renderFactory.renderEnemy(), new Point2DImp(0, 0), new Point2DImp(0, 0));
    }

    @Override
    public void updateManaLabel(final int unspent, final int max) {
        lblMana.setText("Mana: " + String.valueOf(unspent) + " / " + String.valueOf(max));
    }

    @Override
    public void updateEnemyStats(final int health, final int shield) {
        lblEnemyHealth.setText(String.valueOf(health));
        lblEnemyShield.setText(String.valueOf(shield));
    }

    @Override
    public void updatePlayerStats(final int health, final int shield) {
        lblPlayerHealth.setText(String.valueOf(health));
        lblPlayerShield.setText(String.valueOf(shield));
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


        hbPlayerHand.getChildren().add(card);
    }

    public void resetHand() {
        int nCards = hbPlayerHand.getChildren().size();
        for (int i = 0; i < nCards; i++) {
            hbPlayerHand.getChildren().remove(0);
        }
    }

    public void showDamageToEnemy(final int amount) {
        lblEnemyDamage.setText("-" + String.valueOf(amount));
        lblEnemyDamage.setVisible(true);
        lblPlayerDamage.setVisible(false);
    }

    public void showDamageToPlayer(final int amount) {
        lblPlayerDamage.setText("-" + String.valueOf(amount));
        lblPlayerDamage.setVisible(true);
        lblEnemyDamage.setVisible(false);
    }

    public void displayNotEnoughMana() {
        /**
         * This is used to display the "Not enough mana!" message on the GUI for TIME_BEFORE_HIDING_MESSAGE milliseconds
         *  before automatically hiding it
         * */
        new Thread() {
            public void run() {
                try {
                    lblNoMana.setVisible(true);
                    Thread.sleep(TIME_BEFORE_HIDING_MESSAGE);
                    lblNoMana.setVisible(false);
                } catch (InterruptedException e) {
                    System.out.println("The thread died while sleeping");
                } finally {
                    this.interrupt();
                }
            }
        }.start();
    }

    public void setEndTurnEvent(final EventHandler<ActionEvent> buttonClicked) {
        btnEndTurn.setOnAction(buttonClicked);
    }

    @Override
    public void updateEntity(final Render render, final Point2D x, final Point2D y) {
        // TODO Auto-generated method stub
    }

    public void showMessage(final String title, final String description) {
        Alert message = new Alert(AlertType.INFORMATION);
        message.setTitle(title);
        message.setContentText(description);
        message.showAndWait();
    }

}

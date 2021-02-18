package views;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public final class BattleViewImpl extends Application implements BattleView {

    private static final int SCENE_WIDTH = 1920;
    private static final int SCENE_HEIGHT = 1080;

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
    



    /*public BattleViewImpl(final Stage s) {

    }*/

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts" + File.separator + "battle.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Pogeshi - Battle stage");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws java.io.IOException {
        launch(args);
    }

    @Override
    public void removeCard(final int index) {
        HBPlayerHand.getChildren().remove(index);
    }

    @Override
    public void addCard(final ImageView img) {
        HBPlayerHand.getChildren().add(img);
    }

    @Override
    public void showEnemyDamage(final int amount) {
        LBLPlayerDamage.setVisible(false);
        LBLEnemyDamage.setText("-" + String.valueOf(amount));
        LBLEnemyDamage.setVisible(true);
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



}

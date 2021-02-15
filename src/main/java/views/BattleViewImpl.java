package views;

import java.io.File;

import javafx.application.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public final class BattleViewImpl extends Application implements BattleView {

    private static int SCENE_WIDTH = 1920;
    private static int SCENE_HEIGHT = 1080;

    @FXML
    private HBox HBPlayerHand;
    @FXML
    private Label LBLEnemyDamage;
    @FXML
    private Label LBLPlayerDamage;
    @FXML
    private Label LBLMana;

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

    public void showEnemyDamage(final int amount) {
        LBLPlayerDamage.setVisible(false);
        LBLEnemyDamage.setText("-" + String.valueOf(amount));
        LBLEnemyDamage.setVisible(true);
    }

    public void updateManaLabel(final int unspent, final int max) {
        LBLMana.setText("Mana: " + String.valueOf(unspent) + " / " + String.valueOf(max));
    }


}

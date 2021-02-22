package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Account.Account;
import models.Account.AccountImp;
import views.View;

public final class StatisticsControllerImp implements StatisticsController {

    private Account account;

    @FXML
    private Label labelWins;

    @FXML
    private Label labelLoses;

    @FXML
    private Label labelUnlockedCards;

    public StatisticsControllerImp() {
        account = new AccountImp();
    }

    @Override
    @FXML
    public void initialize() {
        labelWins.setText("Vittorie: " + String.valueOf(account.getStatistics().getWins()));
        labelLoses.setText("Sconfitte:" + String.valueOf(account.getStatistics().getLoses()));
        labelUnlockedCards.setText("Unlocked cards: " + String.valueOf(account.getStatistics().getUnlockedCards()));
    }

    @Override
    @FXML
    public void indietroClick(final MouseEvent  e) {
        changeStage(e, "MainMenu");
    }

    private void changeStage(final MouseEvent  e, final String fxmlFileName) {
        try {
            Parent secondarySceneTree = FXMLLoader.load(ClassLoader.getSystemResource("layouts/" + fxmlFileName + ".fxml"));
            Scene secondaryScene = new Scene(secondarySceneTree);
            Stage stage = (Stage) ((Parent) e.getSource()).getScene().getWindow();
            stage.setScene(secondaryScene);
            stage.show();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public View getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void callBackAction(final Object data) {
        // TODO Auto-generated method stub
    }

}

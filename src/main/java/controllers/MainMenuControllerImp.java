package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Account.Account;
import models.Account.AccountImp;
import notifier.EventBus;
import notifier.GuavaEventBusAdapter;
import views.View;

public final class MainMenuControllerImp implements MainMenuController {

    private final Account account;
    private final EventBus<Integer> event;

    public MainMenuControllerImp() {
        account = new AccountImp();
        event = new GuavaEventBusAdapter<Integer>(new com.google.common.eventbus.EventBus());
    }

    /*TODO: Change it later
    @FXML
    @Override
    public void giocaClick() {
        changeStage(e, "worldMap.fxml");
        event.notifyListener(0);
    }
     */

    @FXML
    @Override
    public void statisticheClick(final MouseEvent  e) {
        changeStage(e, "StatisticsMenu");
    }

    @FXML
    @Override
    public void nuovaPartitaClick() {
        account.deleteSaves();
        giocaClick();
    }

    /*TODO: Change it later
    @FXML
    @Override
    public void formaDeckClick() {
        changeStage(e, "deck_view.fxml");
    }
    */
    @FXML
    @Override
    public void esciClick() {
        System.exit(0);

    }

    @FXML
    @Override
    public void giocaClick() {
        // TODO: Temp function
        //test function
        //account.removeCardFromDeck(new CardImpl(5, 2, 3, "Card2", "res/images/card2.png", "DESC"));
        //account.addCardToDeck(new CardImpl(1, 1, 1, "Card1", "Path1", "description1"));
        //account.save();
        //account.deleteSaves();
        //account.lose();
    }

    @FXML
    @Override
    public void formaDeckClick() {
        // TODO: Temp funcion

    }

    private void changeStage(final MouseEvent  e, final String fxmlFileName) {
        try {
            Parent secondarySceneTree = FXMLLoader.load(ClassLoader.getSystemResource("layouts/" + fxmlFileName + ".fxml"));
            Scene secondaryScene = new Scene(secondarySceneTree);
            Stage stage = (Stage) ((Parent) e.getSource()).getScene().getWindow();
            stage.setScene(secondaryScene);
            stage.show();
        } catch (IOException e1) {
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

package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Account;
import models.AccountImp;

public final class AccountControllerImp implements AccountController {

    private Account account;

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

    /*TODO: Change it later
    @FXML
    @Override
    public void giocaClick() {
        final Scene scene = SceneManager.of(LAYOUT.WORLDMAP).getScene();
        this.setTitle("WorldMap");
        this.setScene(scene);
        this.show();
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
        // TODO: Delete save file
        giocaClick();
    }
    /*TODO: Change it later
    @FXML
    @Override
    public void formaDeckClick() {
        final Scene scene = SceneManager.of("layout/DeckMenu").getScene();
        this.setTitle("DeckFormation");
        this.setScene(scene);
        this.show();

    }
    */
    @FXML
    @Override
    public void esciClick() {
        System.exit(0);

    }

    @Override
    public void indietroClick(final MouseEvent  e) {
        changeStage(e, "MainMenu");
    }

    @Override
    public void giocaClick() {
        // TODO: Temp function
        //test function
        try {
            account = new AccountImp();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void formaDeckClick() {
        // TODO: Temp funcion

    }

}

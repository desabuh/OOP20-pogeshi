package controllers;

import java.io.IOException;
import java.util.Optional;

import com.google.common.base.Suppliers;
import com.google.inject.Inject;

import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import controllers.maincontroller.SwitchControllerRequest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import models.Account.Account;
import models.deck.Deck;
import notifier.EventBus;
import views.AccountView;
import views.View;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

public final class MainMenuControllerImp implements MainMenuController {

    private final Account account;
    private final EventBus<Request<LAYOUT, ? extends Object>> notifier;
    private final AccountView accountView;

    @Inject
    public MainMenuControllerImp(final Account account, final AccountView accountView, final MainController mainController, final EventBus<Request<LAYOUT, ? extends Object>> notifier) {
        this.account = account;
        this.accountView = accountView;
        this.notifier = notifier;
        this.notifier.register(mainController);
    }

    public void initialize() {
        Platform.runLater(() -> {
            this.accountView.setScene(SceneManager.of(LAYOUT.ACCOUNT).getScene());
            this.accountView.initializeParams();
        });
    }

    @FXML
    @Override
    public void giocaClick() {
        this.notifier.notifyListener(new SwitchControllerRequest<LAYOUT, Deck>(LAYOUT.WORLDMAP, Suppliers.ofInstance(this.account.getDeck())));
    }

    @FXML
    @Override
    public void nuovaPartitaClick() {
        account.deleteSaves();
        giocaClick();
    }

    @FXML
    @Override
    public void formaDeckClick() {
        this.notifier.notifyListener(new SwitchControllerRequest<LAYOUT, Optional<?>>(LAYOUT.DECKCREATION, Suppliers.ofInstance(Optional.empty())));
    }

    @FXML
    @Override
    public void statisticheClick(final MouseEvent  e) {
        this.accountView.changeToStatistics(account.getStatistics().getWins(), account.getStatistics().getLoses(), account.getStatistics().getUnlockedCards());
    }

    @FXML
    @Override
    public void indietroClick(final MouseEvent  e) {
        this.accountView.changeBackFromStatistics();
    }

    @FXML
    @Override
    public void esciClick() {
        System.exit(0);

    }

    @FXML
    @Override
    public void nextClick(final MouseEvent  e) {
        this.accountView.changeBackFromEnd();
    }

    @Override
    public View getView() {
        return this.accountView;
    }

    @Override
    public void callBackAction(final Object data) {
        if (data instanceof Boolean) {
            this.accountView.changeToEnd();
            if ((boolean) data) {
                this.accountView.changeEndLabelText("VITTORIA");
                try {
                    this.account.win();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                this.accountView.changeEndLabelText("SCONFITTA");
                this.account.lose();
            }
        }
    }

}

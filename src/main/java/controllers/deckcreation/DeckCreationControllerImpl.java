package controllers.deckcreation;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.common.base.Suppliers;
import com.google.inject.Inject;

import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import controllers.maincontroller.SwitchControllerRequest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import models.Account.Account;
import models.deck.card.Card;
import notifier.EventBus;
import views.View;
import views.deckcreation.DeckCreationView;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

/**
 *  A {@link controllers.deckcreation.DeckCreationController} implementation.
 */
public final class DeckCreationControllerImpl implements DeckCreationController {

    private final Account playerAccount;
    private final DeckCreationView deckCreationView;
    private final EventBus<Request<LAYOUT, ? extends Object>> notifier;
    private List<Card> cards;

    @FXML
    private ListView<String> listDeck;
    @FXML
    private ListView<String> listCards;

    @Inject
    public DeckCreationControllerImpl(final Account account, final DeckCreationView deckCreationView, final MainController mainController, final EventBus<Request<LAYOUT, ? extends Object>> notifier) {
        this.playerAccount = account;
        this.deckCreationView = deckCreationView;
        this.notifier = notifier;
        this.notifier.register(mainController);
    }

    /**
     * Initialize the deck controller.
     */
    public void initialize() {
        this.cards = new LinkedList<>(this.playerAccount.getDeck().getCards());
        this.cards.addAll(this.playerAccount.getRemainingCards());
        Platform.runLater(() -> {
            this.deckCreationView.setScene(SceneManager.of(LAYOUT.DECKCREATION).getScene());
            this.deckCreationView.initialize(this.playerAccount.getRemainingCards(), this.playerAccount.getDeck().getCards());
        });
    }

    @Override
    public void changeCardDescription() {
        this.deckCreationView.changeCardDescription();
    }

    @Override
    public void removeCardFromDeck() {
        if (this.playerAccount.getDeck().isDeckFull()  && !this.listDeck.getSelectionModel().isEmpty()) {
            final Card cardToRemove = this.playerAccount.getDeck().getCards().stream()
                    .filter(c -> c.getName().equals(this.listDeck.getSelectionModel().getSelectedItem()))
                    .findAny()
                    .get();
            this.playerAccount.removeCardFromDeck(cardToRemove);
            this.deckCreationView.removeCardFromDeck();
        }
    }

    @Override
    public void addCardToDeck() {
        if (!this.playerAccount.getDeck().isDeckFull() && !this.listCards.getSelectionModel().isEmpty()) {
            final Card cardToAdd = this.cards.stream()
                    .filter(c -> c.getName().equals(this.listCards.getSelectionModel().getSelectedItem()))
                    .findAny()
                    .get();
            this.playerAccount.addCardToDeck(cardToAdd);
            this.deckCreationView.addCardToDeck();
        }
    }

    @Override
    public void saveDeck() {
        if (this.playerAccount.getDeck().isDeckFull()) {
            this.playerAccount.save();
            this.notifier.notifyListener(new SwitchControllerRequest<LAYOUT, Optional<?>>(LAYOUT.ACCOUNT, Suppliers.ofInstance(Optional.empty())));
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("The player's deck isn't full!");
            alert.setContentText("You cannot save the deck if it isn't complete!");
            alert.showAndWait();
        }
    }

    @Override
    public View getView() {
        return this.deckCreationView;
    }

    @Override
    public void callBackAction(final Object data) { 
        this.playerAccount.loadSaves();
    }
}
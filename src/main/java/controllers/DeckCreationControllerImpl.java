package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.Account;
import models.AccountImp;
import models.Card;

/**
 *  A {@link controllers.DeckCreationController} implementation.
 */
public final class DeckCreationControllerImpl implements DeckCreationController {

    private Account playerAccount = new AccountImp();

    @FXML
    private ListView<String> listDeck;
    @FXML
    private ListView<String> listCards;
    @FXML
    private ImageView imgCard;
    @FXML
    private Label lblCardName;
    @FXML
    private Label lblCardDesc;
    @FXML
    private Label lblCardShield;
    @FXML
    private Label lblCardAtk;
    @FXML
    private Label lblCardCost;
    @FXML
    private Label lblNumCards;
    private List<Card> cards;

    /**
     * Start blocking the outside cards list and loading cards from json.
     */
    public void initialize() {
        this.cards = new LinkedList<>(this.playerAccount.getDeck().getCards());
        this.cards.addAll(this.playerAccount.getRemainingCards());
        this.playerAccount.getDeck().getCards().stream().forEach(card -> this.listDeck.getItems().add(card.getName()));
        this.cards.stream().filter(card -> !this.playerAccount.getDeck().isCardInDeck(card)).forEach(card -> this.listCards.getItems().add(card.getName()));
        this.listCards.setDisable(true);
        this.listDeck.setDisable(false);
        this.listDeck.getSelectionModel().select(0);
        this.changeCardDescription();
    }

    @Override
    public void changeCardDescription() {
        Card card;
        Image img;

        if (this.listCards.isDisable()) {
            card = this.cards.stream()
                             .filter(c -> c.getName().equals(this.listDeck.getSelectionModel().getSelectedItem()))
                             .findAny()
                             .get();
        } else {
            card = this.cards.stream()
                             .filter(c -> c.getName().equals(this.listCards.getSelectionModel().getSelectedItem()))
                             .findAny()
                             .get(); 
        }

        try {
            img = new Image(new FileInputStream(card.getResourcePath()));
            this.imgCard.setImage(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.lblCardName.setText(card.getName());
        this.lblCardDesc.setText(card.getDescription());
        this.lblCardCost.setText(String.valueOf(card.getCost()));
        this.lblCardAtk.setText(String.valueOf(card.getAttack()));
        this.lblCardShield.setText(String.valueOf(card.getShield()));
    }

    @Override
    public void removeCardFromDeck() {
        if (this.playerAccount.getDeck().isDeckFull()) {
            Card cardToRemove = this.playerAccount.getDeck().getCards().stream()
                                                                       .filter(c -> c.getName().equals(this.listDeck.getSelectionModel().getSelectedItem()))
                                                                       .findAny()
                                                                       .get();
            this.playerAccount.removeCardFromDeck(cardToRemove);
            this.listCards.getItems().add(this.listDeck.getSelectionModel().getSelectedItem());
            this.listDeck.getItems().remove(this.listDeck.getSelectionModel().getSelectedIndex());
            this.listCards.setDisable(false);
            this.listDeck.setDisable(true);
            this.lblNumCards.setText("9/10");
            this.lblNumCards.setTextFill(Color.RED);
        }
    }

    @Override
    public void addCardToDeck() {
        if (!this.playerAccount.getDeck().isDeckFull()) {
            Card cardToAdd = this.cards.stream()
                                       .filter(c -> c.getName().equals(this.listCards.getSelectionModel().getSelectedItem()))
                                       .findAny()
                                       .get();
            this.playerAccount.addCardToDeck(cardToAdd);
            this.listDeck.getItems().add(this.listCards.getSelectionModel().getSelectedItem());
            this.listCards.getItems().remove(this.listCards.getSelectionModel().getSelectedIndex());
            this.listCards.setDisable(true);
            this.listDeck.setDisable(false);
            this.lblNumCards.setText("10/10");
            this.lblNumCards.setTextFill(Color.LIME);
        }
    }

    @Override
    public void saveDeck() {
        if (this.playerAccount.getDeck().isDeckFull()) {
            this.playerAccount.save();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attenzione");
            alert.setHeaderText("Il mazzo del giocatore non è completo.");
            alert.setContentText("Non si può salvare il mazzo se non è completo!");
            alert.showAndWait();
        }
    }

    @Override
    public void exitWhitoutSaving() {
    }
}

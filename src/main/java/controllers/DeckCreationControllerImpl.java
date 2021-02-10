package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.Card;
import models.CardImpl;
import models.Deck;
import models.DeckImpl;

public final class DeckCreationControllerImpl implements DeckCreationController {

    private Deck deck = new DeckImpl();
//    private Account playerAccount = new AccountImp();
//    private Deck deck = playerAccount.getDeck();

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

    public void initialize() {
        this.listCards.setEditable(false);
        Gson gson = new Gson();
        try {
            Type t = new TypeToken<LinkedList<CardImpl>>() { }.getType();
            this.cards = gson.fromJson(new FileReader("res" + File.separator + "jsons" + File.separator + "ListOfCards.json"), t);
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            e.printStackTrace();
        }

        this.deck.getCards().stream().forEach(card -> this.listDeck.getItems().add(card.getName()));
        this.cards.stream().filter(card -> !this.deck.isCardInDeck(card)).forEach(card -> this.listCards.getItems().add(card.getName()));
        this.listCards.setDisable(true);
        this.listDeck.setDisable(false);
    }

    @Override
    public void changeCardDescription() {
        Card card;
        if (this.listCards.isDisable()) {
            card = this.cards.stream()
                    .filter(c -> c.getName().equals(this.listDeck.getSelectionModel().getSelectedItem()))
                    .findAny().get();
        } else {
            card = this.cards.stream()
                    .filter(c -> c.getName().equals(this.listCards.getSelectionModel().getSelectedItem()))
                    .findAny().get();
        }

        Image img;
        try {
            img = new Image(new FileInputStream(card.getResourcePath()));
            this.lblCardName.setText(card.getName());
            this.lblCardDesc.setText(card.getDescription());
            this.lblCardCost.setText(String.valueOf(card.getCost()));
            this.lblCardAtk.setText(String.valueOf(card.getAttack()));
            this.lblCardShield.setText(String.valueOf(card.getShield()));
            this.imgCard.setImage(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCardFromDeck() {
        if (this.listDeck.getItems().size() == 10) {
            Card cardToRemove = this.deck.getCards().stream().filter(c -> c.getName().equals(this.listDeck.getSelectionModel().getSelectedItem())).findAny().get();
            this.deck.removeCard(cardToRemove);
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
        if (this.listDeck.getItems().size() == 9) {
            Card cardToAdd = this.cards.stream().filter(c -> c.getName().equals(this.listCards.getSelectionModel().getSelectedItem())).findAny().get();
            this.deck.addCard(cardToAdd);
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
//        this.playerAccount.save();
    }

    @Override
    public void exitWhitoutSaving() {
    }
}

package views.deckcreation;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.GameMap.Point2D;
import models.deck.card.Card;
import views.JavafxView;
import views.render.Render;
import views.scene.layout.LAYOUT;

/**
 * A {@link views.deckcreation.DeckCreationView} implementation.
 */
public final class DeckCreationViewImpl extends JavafxView implements DeckCreationView {

    private ListView<String> listDeck;
    private ListView<String> listCards;
    private List<Card> cards;
    private ImageView imgCard;
    private Label lblCardName;
    private Label lblCardDesc;
    private Label lblCardShield;
    private Label lblCardAtk;
    private Label lblCardCost;
    private Label lblNumCards;

    /**
     * Standard constructor.
     * @param stage the stage to be displayed
     */
    public DeckCreationViewImpl(final Stage stage) {
        super(stage, LAYOUT.DECKCREATION);
    }

    @Override
    public void updateEntity(final Render render, final Point2D x, final Point2D y) { }

    //In this case it is not possible to receive another object than a ListView<String>.
    @SuppressWarnings("unchecked")
    @Override
    public void initialize(final List<Card> remainingCards, final List<Card> playerDeck) {
        this.cards = new LinkedList<>(remainingCards);
        this.cards.addAll(playerDeck);
        this.listDeck = (ListView<String>) this.getScene().lookup("#listDeck");
        this.listCards = (ListView<String>) this.getScene().lookup("#listCards");
        this.imgCard = (ImageView) this.getScene().lookup("#imgCard");
        this.lblCardName = (Label) this.getScene().lookup("#lblCardName");
        this.lblCardDesc = (Label) this.getScene().lookup("#lblCardDesc");
        this.lblCardShield = (Label) this.getScene().lookup("#lblCardShield");
        this.lblCardAtk = (Label) this.getScene().lookup("#lblCardAtk");
        this.lblCardCost = (Label) this.getScene().lookup("#lblCardCost");
        this.lblNumCards = (Label) this.getScene().lookup("#lblNumCards");
        remainingCards.stream().forEach(card -> this.listCards.getItems().add(card.getName()));
        playerDeck.stream().forEach(card -> this.listDeck.getItems().add(card.getName()));
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
            card = cards.stream()
                    .filter(c -> c.getName().equals(this.listDeck.getSelectionModel().getSelectedItem()))
                    .findAny()
                    .get();
        } else {
            card = cards.stream()
                    .filter(c -> c.getName().equals(this.listCards.getSelectionModel().getSelectedItem()))
                    .findAny()
                    .get(); 
        }


        img = new Image(this.getClass().getResourceAsStream(card.getResourcePath()));
        this.imgCard.setImage(img);


        this.lblCardName.setText(card.getName());
        this.lblCardDesc.setText(card.getDescription());
        this.lblCardCost.setText(String.valueOf(card.getCost()));
        this.lblCardAtk.setText(String.valueOf(card.getAttack()));
        this.lblCardShield.setText(String.valueOf(card.getShield()));
    }

    @Override
    public void removeCardFromDeck() {
        this.listCards.getItems().add(this.listDeck.getSelectionModel().getSelectedItem());
        this.listDeck.getItems().remove(this.listDeck.getSelectionModel().getSelectedIndex());
        this.listCards.setDisable(false);
        this.listDeck.setDisable(true);
        this.lblNumCards.setText("9/10");
        this.lblNumCards.setTextFill(Color.RED);
    }

    @Override
    public void addCardToDeck() {
        this.listDeck.getItems().add(this.listCards.getSelectionModel().getSelectedItem());
        this.listCards.getItems().remove(this.listCards.getSelectionModel().getSelectedIndex());
        this.listCards.setDisable(true);
        this.listDeck.setDisable(false);
        this.lblNumCards.setText("10/10");
        this.lblNumCards.setTextFill(Color.LIME);
    }

}

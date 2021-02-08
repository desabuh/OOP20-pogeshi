package views;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Card;

public final class DeckCreationViewImpl extends Pane implements DeckCreationView {

    @FXML
    private GridPane gridDeck;
    @FXML
    private GridPane gridCards;
    @FXML
    private Label lblCardName;
    @FXML
    private Label lblCardDesc;
    @FXML
    private Label lblCardDef;
    @FXML
    private Label lblCardAtk;
    @FXML
    private Label lblCardCost;

    @Override
    public void removeCardFromDeck(final Card card) {
        Label lbl = new Label(card.getName());
        Button btn = new Button("Rimuovi");
        Node node = this.gridDeck.getChildren().stream()
                        .filter(n -> GridPane.getColumnIndex(n) == 0)
                        .filter(n -> card.getName().equals(n.getAccessibleText()))
                        .findAny().get();
        this.gridDeck.getChildren().removeIf(n -> GridPane.getRowIndex(n).equals(GridPane.getRowIndex(node)));
        this.gridCards.addRow(0, lbl, btn);
    }

    @Override
    public void addCardToDeck(final Card card) {
        Label lbl = new Label(card.getName());
        Button btn = new Button("Aggiungi");
        Node node = this.gridCards.getChildren().stream()
                        .filter((n) -> GridPane.getColumnIndex(n) == 0)
                        .filter((n) ->  card.getName().equals(n.getAccessibleText()))
                        .findAny().get();
        this.gridCards.getChildren().removeIf(n -> GridPane.getRowIndex(n).equals(GridPane.getRowIndex(node)));
        this.gridDeck.addRow(0, lbl, btn);
    }

    @Override
    public void changeCardDisplayed(final Card card) {
        this.lblCardName.setText(card.getName());
        this.lblCardDesc.setText(card.getDescription());
        this.lblCardCost.setText(Integer.toString(card.getCost()));
        this.lblCardAtk.setText(Integer.toString(card.getAttack()));
        this.lblCardDef.setText(Integer.toString(card.getDefense()));
    }

}

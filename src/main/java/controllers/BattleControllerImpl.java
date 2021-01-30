package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import models.*;

public class BattleControllerImpl implements BattleController {
    
    private Player p = new PlayerImpl(30);
    private Battle b = new BattleImpl();

    @FXML
    private Label LBLPlayerHealth;
    @FXML
    private HBox HBPlayerHand;

    @FXML
    public void initialize() {
        p.addCard(new CardImpl("Carta prova", 2, 3, 0));
        p.addCard(new CardImpl("Carta prova 2", 1, 1, 0));
        LBLPlayerHealth.setText(String.valueOf(p.getHealth()));
        for(Card c : p.getHand()) {
            Button b = new Button(c.getName());
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedCard(c);
                }
                
            });
            HBPlayerHand.getChildren().add(new Button(c.getName()));
        }
    }
    
    public void selectedCard(Card c) {
        
    }
    
}

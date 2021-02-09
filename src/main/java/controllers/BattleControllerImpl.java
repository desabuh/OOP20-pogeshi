package controllers;

import java.util.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import models.*;

public class BattleControllerImpl implements BattleController {
    
    private Battle b = new BattleImpl();

    @FXML
    private Label LBLPlayerHealth;
    @FXML
    private HBox HBPlayerHand;
    @FXML
    private Label LBLEnemyDamage;
    @FXML
    private Label LBLAvailableMana;

    @FXML
    public void initialize() {
        System.out.println("ACTIVE");
    }
    
    private void selectedCard(int index) {

    }
    
    public void setTest() {
        
    }
    
    private void updateHand(int startingIndex) {

    }
    
}

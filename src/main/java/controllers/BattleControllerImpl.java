package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BattleControllerImpl implements BattleController {
    
    @FXML
    private Label LBLPlayerHealth;

    @FXML
    public void initialize() {
       LBLPlayerHealth.setText("10");
    }
    
}

package models;

import controllers.Card;
import controllers.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BattleImpl implements Battle {
    
    private Turn turn = Turn.PLAYER;
    
    @FXML
    private Label LBLEnemyDamage;


    @Override
    public void endTurn() {
        if(turn == Turn.PLAYER) {
            turn = Turn.ENEMY;
        }
        else {
            turn = Turn.PLAYER;
        }
    }

    @Override
    public boolean checkBattleEnd() {
        // TODO Auto-generated method stub
        return false;
    }
    
    public boolean playCard(Card c, Player p) {
        if(p.getUnusedCombatMana() >= c.getCost()) {
            p.setUnusedCombatMana(p.getUnusedCombatMana() - c.getCost());
            return true;
        }
        return false;
    }

}
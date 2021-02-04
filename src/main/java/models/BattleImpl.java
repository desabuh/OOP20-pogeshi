package models;

import java.util.Optional;

import controllers.Card;
import controllers.EnemyImpl;
import controllers.Player;
import controllers.PlayerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BattleImpl implements Battle {

    private Turn turn = Turn.PLAYER;

    @FXML
    private Label LBLEnemyDamage;

    @Override
    public void endTurn() {
        turn = (turn == Turn.PLAYER) ? Turn.ENEMY : Turn.PLAYER;
    }

    @Override
    public boolean checkBattleEnd() {
        // TODO Auto-generated method stub
        return false;
    }

    public Optional<Player> playCard(final Card c, int mana) {
        if(mana >= c.getCost()) {
            Character g = new PlayerImpl(0);
            return turn == Turn.PLAYER ? Optional.of(g) : Optional.of(new EnemyImpl(0));
        }
        return Optional.empty();
       /* if (p.getUnusedCombatMana() >= c.getCost()) {
            p.setUnusedCombatMana(p.getUnusedCombatMana() - c.getCost());
            return true;
        }
        return false;*/
    }

}

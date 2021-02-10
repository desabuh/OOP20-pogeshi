package models;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public final class BattleImpl implements Battle {

    private Turn turn = Turn.PLAYER;

    @FXML
    private Label LBLEnemyDamage;

    @Override
    public void endTurn() {
        turn = (turn == Turn.PLAYER) ? Turn.ENEMY : Turn.PLAYER;
    }

    /*public Optional<? extends Character> playCard(final Card c, int mana) {
        if(mana >= c.getCost()) {
            return currentTurn();
        }
        return Optional.empty();
    }*/

    @Override
    public Character currentTurn() {
        return turn == Turn.PLAYER ? new PlayerImp(new DeckImpl()) : new EnemyImp(new DeckImpl());
    }

    @Override
    public boolean isPlayable(final Card c, final int mana) {
        return turn == Turn.ENEMY ? true : mana >= c.getCost();
    }

    @Override
    public boolean checkBattleEnd(final int healthPlayer, final int healthOpponent) {
        return healthPlayer <= 0 || healthOpponent <= 0;
    }

}
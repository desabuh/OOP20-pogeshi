package models;

import controllers.Card;

public class BattleImpl implements Battle {
    
    private Turn turn = Turn.PLAYER;

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
    
    public void playCard(Card c) {
        
    }

}

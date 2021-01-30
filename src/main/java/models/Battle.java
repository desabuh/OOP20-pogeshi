/**
 * 
 */
package models;

import controllers.Card;

public interface Battle {

    void endTurn();
    
    boolean checkBattleEnd();
    
    void playCard(Card c);
    
    public enum Turn{
        PLAYER,
        ENEMY
    }
    
}

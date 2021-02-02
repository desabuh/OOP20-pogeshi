/**
 * 
 */
package models;

import controllers.Card;
import controllers.Player;

public interface Battle {

    void endTurn();
    
    boolean checkBattleEnd();
    
    boolean playCard(Card c, Player p);
    
    public enum Turn{
        PLAYER,
        ENEMY
    }
    
}

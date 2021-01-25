/**
 * 
 */
package models;

public interface Battle {

    void endTurn();
    
    boolean checkBattleEnd();
    
    public enum Turn{
        PLAYER,
        ENEMY
    }
    
}

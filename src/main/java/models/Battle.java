/**
 * 
 */
package models;

public interface Battle {
    /**
     * Switches the turn from player to enemy or viceversa.
     * */
    void endTurn();

    /**
     * Checks if the battle needs to end.
     * @return TRUE if it needs to end, FALSE otherwise
     * */
    boolean checkBattleEnd();

    /**
     * @return the instance of the character whose turn is.
     * @implNote Useful since the Battle controller does not know which turn currently is, but has only the instances of the characters that play the battle
     * */
    Character currentTurn();

    /**
     * Tries to play a card specified by the index
     * @param index The index of the card in the current turn's character's hand
     * @return TRUE if the card could be played, FALSE otherwise
     * */
    boolean playCard(int index);

    Player getPlayer();

    EnemyImp getEnemy();

    void initializeCharacters();

    int getPlayerUnusedCombatMana();

    /**
     * Enum indicating the current turn.
     * */
    enum Turn {
        PLAYER,
        ENEMY
    }
}


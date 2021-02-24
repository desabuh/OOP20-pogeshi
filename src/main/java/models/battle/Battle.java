/**
 * 
 */
package models.battle;

import models.Character.Character;
import models.Character.EnemyImp;
import models.Character.Player;

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
     * Tries to play a card specified by the index.
     * @param index The index of the card in the current turn's character's hand
     * @return TRUE if the card could be played, FALSE otherwise
     * */
    boolean playCard(int index);

    /**
     * @return The player that is currently playing
     * */
    Player getPlayer();

    /**
     * @return The enemy that is currently playing
     * */
    EnemyImp getEnemy();

    /**
     * Initializes the player and the enemy, allowing the battle to start.
     * @throws IllegalStateException if the characters are already initialized.
     * @implNote Useful since when the model is instantiated, the main controller does not know the player's status, and needs
     * to be passed to the model in another moment. The characters, therefore, can't be initialized when the model is created.
     * */
    void initializeCharacters();

    /**
     * @return The amount of available mana left for the current turn
     * */
    int getPlayerUnusedCombatMana();

    /**
     * Sets the player that will play the battle.
     * @param player The player to use in the battle
     * @implNote This is used since the player is not known at call-time and needs to be set in another moment.
     * */
    void setPlayer(Player player);

    /**
     * Enum indicating the current turn.
     * */
    enum Turn {
        PLAYER,
        ENEMY
    }

    /**
     * @return TRUE if the player won the battle, FALSE otherwise
     * @throws IllegalStateException if the battle has not ended yet
     * */
    boolean hasPlayerWon();
}


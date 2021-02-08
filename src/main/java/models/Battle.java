/**
 * 
 */
package models;

import controllers.Card;
import controllers.Character;

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

    //Optional<? extends Character> playCard(Card c, int mana);
    /**
     * Since the controller doesn't know the current turn.
     * @return the instance of the character whose turn is
     * */
    Character currentTurn();

    /**
     * Checks if the card is playable with a certain amount of mana.
     * @param c: the card that needs to the played
     * @param mana: the mana of the character that needs to play the card
     * @return TRUE if the card can be played, FALSE otherwise
     * */
    boolean isPlayable(Card c, int mana);

    /**
     * Enum indicating the current turn.
     * */
    enum Turn {
        PLAYER,
        ENEMY
    }

}

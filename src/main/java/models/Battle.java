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

    //Optional<? extends Character> playCard(Card c, int mana);
    /**
     * @return the instance of the character whose turn is.
     * @implNote Useful since the Battle controller does not know which turn currently is, but has only the instances of the characters that play the battle
     * */
    Character currentTurn();

    /**
     * Checks if the card is playable with a certain amount of mana.
     * @param c The card that needs to be played
     * @param mana The amount of mana available
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

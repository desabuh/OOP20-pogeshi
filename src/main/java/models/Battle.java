/**
 * 
 */
package models;

import java.util.Optional;

import controllers.Card;
import controllers.Player;
import controllers.Character;

public interface Battle {

    void endTurn();

    boolean checkBattleEnd();

    //Optional<? extends Character> playCard(Card c, int mana);

    Character currentTurn();

    boolean isPlayable(Card c, int mana);

    enum Turn {
        PLAYER,
        ENEMY
    }

}

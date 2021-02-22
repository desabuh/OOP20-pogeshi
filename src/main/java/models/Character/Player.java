package models.Character;

import models.Hand;
import models.GameMap.Point2D;

public interface Player extends Character {

    /**
     * Set the {@code position} of the player.
     * @param destination   {@code Point2D destination} of the new position.
     */
    void setPosition(Point2D destination);

    /**
     * Return the {@code mana} of the {@code Character}.
     * @return  Value of {@code mana}.
     */
    int getMana();

    /**
     * Return the {@code hand} of the {@code Character}.
     * @return  Obj {@code hand}.
     */
    Hand getHand();

    /**
     * Set the {@code mana} of the {@code Character}.
     * @param value     The {@code mana} value to be setted.
     */
    void setMana(int value);

}

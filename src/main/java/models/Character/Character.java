package models.Character;

import models.Battle;
import models.Deck;
import models.GameMap.Point2D;

/** description.
 * @param
 * {@link Battle}
 * @return
 */
public interface Character {

    /**
     * Return the {@code health} of the {@code Character}.
     * @return Value of {@code health}.
     */
    int getHealth();

    /**
     * Set the {@code health} of the {@code Character}.
     * @param value     The {@code health} value to be setted.
     */
    void setHealth(int value);

    /**
     * Return a {@code Point2D position} of the {@code Character}.
     * @return  Obj {@code Point2D position}.
     */
    Point2D getPosition();

    /**
     * Return the {@code deck} of the {@code Character}.
     * @return  Obj {@code Deck}.
     */
    Deck getDeck();

    /**
     * Return the {@code shield} of the {@code Character}.
     * @return  Value of {@code shield}
     */
    int getShield();

    /**
     * Set the {@code shield} of the {@code Character}.
     * @param value     The {@code shield} value to be setted.
     */
    void setShield(int value);

}


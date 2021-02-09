package models;

/** description.
 * @param
 * {@link Battle}
 * @return
 */
public interface Character {

    int getHealt();

    void setHealth(int value);

    Point2D getPosition();

    Deck getDeck();

    int getShield();

    void setShield(int value); //TODO: discuss with others, not in the project?

}


package models.Character;

import models.Deck;
import models.GameMap.Point2D;

public class CharacterImp implements Character {

    protected int health;
    protected Point2D position;
    protected Deck deck;
    protected int shield;

    public CharacterImp(final int health, final Point2D position, final Deck deck, final int shield) {
        setHealth(health);
        setShield(shield);
        this.position = position;
        this.deck = deck;
    }

    /**
     * Return the value of the {@code health}.
     */
    @Override
    public final int getHealth() {
        return this.health;
    }

    /**
     * Set the {@code health} equal to the passed value.
     */
    @Override
    public final void setHealth(final int value) {
        this.health = value;
    }

    /**
     * Return the {@code Point2D position}.
     */
    @Override
    public final Point2D getPosition() {
        return this.position;
    }

    /**
     * Return the {@code Deck}.
     */
    @Override
    public final Deck getDeck() {
        return this.deck;
    }

    /**
     * Return the value of the {@code shield}.
     */
    @Override
    public final int getShield() {
        return this.shield;
    }

    /**
     * Set the {@code shield} equal to the passed value.
     */
    @Override
    public final void setShield(final int value) {
        if (value < 0) {
            this.shield = 0;
        } else {
            this.shield = value;
        }
    }
}

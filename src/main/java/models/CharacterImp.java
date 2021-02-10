package models;

public abstract class CharacterImp implements Character {

    protected int health;
    protected Point2D position;
    protected Deck deck;
    protected int shield;

    @Override
    public final int getHealth() {
        return this.health;
    }

    @Override
    public final void setHealth(final int value) {
        this.health = value;
    }

    @Override
    public final Point2D getPosition() {
        return this.position;
    }

    @Override
    public final Deck getDeck() {
        return this.deck;
    }

    @Override
    public final int getShield() {
        return this.shield;
    }

    @Override
    public final void setShield(final int value) {
        if (value < 0) {
            this.shield = 0;
        } else {
            this.shield = value;
        }
    }
}

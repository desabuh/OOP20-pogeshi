package models;

import models.GameMap.Point2D;

public final class EnemyImp extends CharacterImp implements Enemy {

    /**
     * Constructor with default parameters and custom deck and position.
     * health = 100
     * shield = 0
     * @param deck
     * @param position
     */
    public EnemyImp(final Deck deck, final Point2D position) {
        setHealth(100);
        setShield(0);
        this.deck = deck;
        this.position = position;
    }

    /**
     * Constructor with default shield and custom health, deck and position.
     * @param deck
     * @param health
     * @param position
     */
    public EnemyImp(final Deck deck, final int health, final Point2D position) {
        setHealth(health);
        setShield(0);
        this.deck = deck;
        this.position = position;
    }

    /**
     * Constructor with custom parameters.
     * @param deck
     * @param healt
     * @param shield
     * @param position
     */
    public EnemyImp(final Deck deck, final int healt, final int shield, final Point2D position) {
        setHealth(healt);
        setShield(shield);
        this.deck = deck;
        this.position = position;
    }

}

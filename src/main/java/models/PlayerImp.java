package models;

import models.GameMap.Point2D;
import models.GameMap.Point2DImp;

public final class PlayerImp extends CharacterImp implements Player {

    private int mana;
    private Hand hand;
    private final int maxMana = 3;  //TODO: not sure if i should do it like this or keep it a number

    /**
     * Constructor with default parameters and custom deck.
     * health = 100
     * shield = 0
     * mana = 1
     * position = (0, 0)
     * @param deck
     */
    public PlayerImp(final Deck deck) {
        setHealth(100);
        setShield(0);
        setMana(1);
        setPosition(new Point2DImp(0, 0));
        this.deck = deck;
        this.hand = new HandImpl();  //TODO: modify in case the implemantation name isn't HandImp
    }

    /**
     * Constructor with custom parameters.
     * @param deck
     * @param health
     * @param shield
     * @param mana
     * @param position
     */
    public PlayerImp(final Deck deck, final int health, final int shield, final int mana, final Point2D position) {
        setHealth(health);
        setShield(shield);
        setMana(mana);
        setPosition(position);
        this.hand = new HandImpl();  //TODO: modify in case the implemantation name isn't HandImp
    }

    /**
     * Constructor with default mana and shield but custom deck, health and position.
     * @param deck
     * @param health
     * @param position
     */
    public PlayerImp(final Deck deck, final int health, final Point2D position) {
        setHealth(health);
        setShield(0);
        setMana(1);
        setPosition(position);
        this.hand = new HandImpl();  //TODO: modify in case the implemantation name isn't HandImp
    }

    @Override
    public void setPosition(final Point2D destination) {
        this.position = destination;
    }

    @Override
    public int getMana() {
        return this.mana;
    }

    @Override
    public void setMana(final int value) {
        if (!(value < 0 || value > this.maxMana)) {
            this.mana = value;
        }
    }

    @Override
    public Hand getHand() {
        return this.hand;
    }

}

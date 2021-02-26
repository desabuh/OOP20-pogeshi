package models.Character;

import models.GameMap.Point2D;
import models.GameMap.Point2DImp;
import models.battle.Hand;
import models.battle.HandImpl;
import models.deck.Deck;

public final class PlayerImp extends CharacterImp implements Player {

    private int mana;
    private Hand hand;
    private final int maxMana = 10;

    /**
     * Constructor with default parameters and custom deck.
     * <p>
     * <ul>
     * <li>{@code health} = 100</li>
     * <li>{@code shield} = 0</li>
     * <li>{@code mana} = 1</li>
     * <li>{@code position} = (0, 0)</li>
     * </ul>
     * <p>
     * @param deck  The {@code deck} of the {@code player}.
     */
    public PlayerImp(final Deck deck) {
        super(200, new Point2DImp(0, 0), deck, 0);
        setMana(1);
        this.hand = new HandImpl();
    }

    /**
     * Constructor with custom parameters.
     * @param deck      The {@code deck} of the {@code player}.
     * @param health    The {@code health} of the {@code player}.
     * @param shield    The {@code shield} of the {@code player}. 
     * @param mana      The {@code mana} of the {@code player}.
     * @param position  The {@code position} of the {@code player}.
     */
    public PlayerImp(final Deck deck, final int health, final int shield, final int mana, final Point2D position) {
        super(health, position, deck, shield);
        setMana(mana);
        this.hand = new HandImpl();
    }

    /**
     * Constructor with default mana and shield but custom deck, health and position.
     * <p>
     * <ul>
     * <li>{@code shield} = 0</li>
     * <li>{@code mana} = 1</li>
     * </ul>
     * <p>
     * @param deck      The {@code deck} of the {@code player}.
     * @param health    The {@code health} of the {@code player}.
     * @param position  The {@code position} of the {@code player}.
     */
    public PlayerImp(final Deck deck, final int health, final Point2D position) {
        super(health, position, deck, 0);
        setMana(1);
        this.hand = new HandImpl();
    }

    /**
     * Set the {@code position} equal to the passed position.
     */
    @Override
    public void setPosition(final Point2D destination) {
        super.setPosition(destination);
    }

    /**
     * Return the value of the {@code mana}.
     */
    @Override
    public int getMana() {
        return this.mana;
    }

    /**
     * Set the {@code mana} equals to the passed value.
     */
    @Override
    public void setMana(final int value) {
        if (!(value < 0 || value > this.maxMana)) {
            this.mana = value;
        }
    }

    /**
     * Return the value of the {@code hand}.
     */
    @Override
    public Hand getHand() {
        return this.hand;
    }

}

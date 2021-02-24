package models.Character;

import models.GameMap.Point2D;
import models.deck.Deck;

public final class EnemyImp extends CharacterImp implements Enemy {

    /**
     * Constructor with default parameters and custom deck and position.
     * <p>
     * <ul>
     * <li>{@code health} = 10</li>
     * <li>{@code shield} = 0</li>
     * </ul>
     * <p>
     * @param deck      The {@code deck} of the {@code enemy}.
     * @param position  The {@code position} of the {@code enemy}.
     */
    public EnemyImp(final Deck deck, final Point2D position) {
        super(10, position, deck, 0);
    }

    /**
     * Constructor with default shield and custom health, deck and position.
     * <p>
     * <ul>
     * <li>{@code shield} = 0</li>
     * </ul>
     * <p>
     * @param deck      The {@code deck} of the {@code enemy}.
     * @param health    The {@code health} of the {@code enemy}.
     * @param position  The {@code position} of the {@code enemy}.
     */
    public EnemyImp(final Deck deck, final int health, final Point2D position) {
        super(health, position, deck, 0);
    }

    /**
     * Constructor with custom parameters.
     * @param deck      The {@code deck} of the {@code enemy}.
     * @param health    The {@code health} of the {@code enemy}.
     * @param shield    The {@code shield} of the {@code enemy}.
     * @param position  The {@code position} of the {@code enemy}.
     */
    public EnemyImp(final Deck deck, final int health, final int shield, final Point2D position) {
        super(health, position, deck, shield);
    }

}

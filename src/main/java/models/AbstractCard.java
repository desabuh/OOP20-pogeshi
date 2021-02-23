package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 *  An abstract Card.
 */
public abstract class AbstractCard implements Card {

    public abstract static class Builder<T extends Builder<T>> {

        private int cost;
        private int attack;
        private int shield;
        private String name;
        private String resourcePath;
        private String description;

        /**
         * Constructs a Card Builder.
         */
        public Builder() { }

        /**
         * Set the name of the card.
         *
         * @param name the name to assign to the card
         * @return this builder
         */
        public T name(final String name) {
            this.name = name;
            return self();
        }

        /**
         * Set the cost of the card.
         *
         * @param cost the cost to assign to the card
         * @return this builder
         */
        public T cost(final int cost) {
            this.cost = cost;
            return self();
        }

        /**
         * Set the resource path of the card image.
         *
         * @param path the path of the card image to assign to the card
         * @return this builder
         * @throws FileNotFoundException when the file indicated by the path does not exist
         */
        public T resourcePath(final String path) throws FileNotFoundException {
            this.resourcePath = path;
            File file = new File(path);
            if(!file.exists()) {
                throw new FileNotFoundException();
            }
            return self();
        }

        /**
         * Set the description of the card.
         *
         * @param description the description to assign to the card
         * @return this builder
         */
        public T description(final String description) {
            this.description = description;
            return self();
        }

        /**
         * Set the attack of the card.
         *
         * @param attack the attack to assign to the card
         * @return this builder
         */
        public T attack(final int attack) {
            this.attack = attack;
            return self();
        }

        /**
         * Set the shield of the card.
         *
         * @param shield the shield to assign to the card
         * @return the builder
         */
        public T shield(final int shield) {
            this.shield = shield;
            return self();
        }

        /**
         * Returns a newly-created Card.
         *
         * @return A card
         */
        abstract Card build();

        abstract T self();
    }

    private int cost;
    private int attack;
    private int shield;
    private String name;
    private String resourcePath;
    private String description;

    protected AbstractCard(final Builder<?> builder) {
        if(builder.cost < 0 || builder.attack < 0 || builder.shield < 0) {
            throw new IllegalStateException("Cost, attack and shield must be >= 0");
        }
        this.cost = builder.cost;
        this.attack = builder.attack;
        this.shield = builder.shield;
        this.name = Objects.requireNonNull(builder.name);
        this.description = Objects.requireNonNull(builder.description);
        this.resourcePath = builder.resourcePath;
    }

    /**
     * Gets the cost.
     *
     * @return The cost of this card
     */
    @Override
    public int getCost() {
        return this.cost;
    }

    /**
     * Gets the attack.
     *
     * @return The attack of this card
     */
    @Override
    public int getAttack() {
        return this.attack;
    }

    /**
     * Gets the shield.
     *
     * @return The shield of this card
     */
    @Override
    public int getShield() {
        return this.shield;
    }

    /**
     * Gets the name.
     *
     * @return The name of this card
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the resource path.
     *
     * @return The name of this card
     */
    @Override
    public String getResourcePath() {
        return this.resourcePath;
    }

    /**
     * Gets the description.
     *
     * @return The Description of this card
     */
    @Override
    public String getDescription() {
        return this.description;
    }

}

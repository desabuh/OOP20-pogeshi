package models;

/**
 *  An abstract Card.
 */
public abstract class AbstractCard implements Card {
    
    public abstract static class Builder<T extends Builder<T>> {

        protected int cost;
        protected int attack;
        protected int shield;
        protected String name;
        protected String resourcePath;
        protected String description;

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
         */
        public T resourcePath(final String path) {
            this.resourcePath = path;
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
        abstract public Card build();
        
        abstract protected T self();
    }
    
    protected int cost;
    protected int attack;
    protected int shield;
    protected String name;
    protected String resourcePath;
    protected String description;
    
    protected AbstractCard(Builder<?> builder) {
        this.cost = builder.cost;
        this.attack = builder.attack;
        this.shield = builder.shield;
        this.name = builder.name;
        this.resourcePath = builder.resourcePath;
        this.description = builder.description;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public int getShield() {
        return this.shield;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getResourcePath() {
        return this.resourcePath;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}

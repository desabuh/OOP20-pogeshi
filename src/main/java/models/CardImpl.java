package models;

/**
 * A {@link models.Card} implementation.
 */
public final class CardImpl implements Card, Comparable<Card> {

    /**
     * A Builder for CardImpl.
     */
    public static final class Builder {

        private int id;
        private int cost;
        private int attack;
        private int shield;
        private String name;
        private String resourcePath;
        private String description;

        /**
         * Constructs a CardImpl Builder.
         */
        public Builder() { }

        /**
         * Set the name of the card.
         *
         * @param name the name to assign to the card
         * @return this builder
         */
        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the cost of the card.
         *
         * @param cost the cost to assign to the card
         * @return this builder
         */
        public Builder cost(final int cost) {
            this.cost = cost;
            return this;
        }

        /**
         * Set the resource path of the card image.
         *
         * @param path the path of the card image to assign to the card
         * @return this builder
         */
        public Builder resourcePath(final String path) {
            this.resourcePath = path;
            return this;
        }

        /**
         * Set the description of the card.
         *
         * @param description the description to assign to the card
         * @return this builder
         */
        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * Set the attack of the card.
         *
         * @param attack the attack to assign to the card
         * @return this builder
         */
        public Builder attack(final int attack) {
            this.attack = attack;
            return this;
        }

        /**
         * Set the shield of the card.
         *
         * @param shield the shield to assign to the card
         * @return the builder
         */
        public Builder shield(final int shield) {
            this.shield = shield;
            return this;
        }

        /**
         * Set the id of the card.
         *
         * @param id the id to assign to the card
         * @return this builder
         */
        public Builder id(final int id) {
            this.id = id;
            return this;
        }

        /**
         * Returns a newly-created Card.
         *
         * @throws IllegalStateException when one or more parameters are not set
         * @return A card
         */
        public Card build() throws IllegalStateException {
            if (this.name == null || this.description == null || this.resourcePath == null) {
                throw new IllegalStateException();
            }
            return new CardImpl(this.attack, this.shield, this.cost, this.name, this.resourcePath, this.description, this.id);
        }
    }

    private int id;
    private int cost;
    private int attack;
    private int shield;
    private String name;
    private String resourcePath;
    private String description;

    private CardImpl(final int attack, final int shield, final int cost, final String name, final String resourcePath, final String description, final int id) {
        this.cost = cost;
        this.attack = attack;
        this.shield = shield;
        this.name = name;
        this.resourcePath = resourcePath;
        this.description = description;
        this.id = id;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + attack;
        result = prime * result + cost;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((resourcePath == null) ? 0 : resourcePath.hashCode());
        result = prime * result + shield;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CardImpl)) {
            return false;
        }
        CardImpl other = (CardImpl) obj;
        if (attack != other.attack) {
            return false;
        }
        if (cost != other.cost) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (resourcePath == null) {
            if (other.resourcePath != null) {
                return false;
            }
        } else if (!resourcePath.equals(other.resourcePath)) {
            return false;
        }
        if (shield != other.shield) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final Card card) {
        return this.name.compareTo(card.getName());
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getId() {
        return this.id;
    }
}

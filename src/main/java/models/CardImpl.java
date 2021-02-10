package models;

public final class CardImpl implements Card, Comparable<Card> {

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

    public static final class Builder {
        private int id;
        private int cost;
        private int attack;
        private int shield;
        private String name;
        private String resourcePath;
        private String description;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder cost(final int cost) {
            this.cost = cost;
            return this;
        }

        public Builder resourcePath(final String path) {
            this.resourcePath = path;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder attack(final int attack) {
            this.attack = attack;
            return this;
        }

        public Builder shield(final int shield) {
            this.shield = shield;
            return this;
        }

        public Builder id(final int id) {
            this.id = id;
            return this;
        }

        public Card build() throws IllegalStateException {
            if (this.name == null || this.description == null || this.resourcePath == null) {
                throw new IllegalStateException("");
            }
            return new CardImpl(this.attack, this.shield, this.cost, this.name, this.resourcePath, this.description, this.id);
        }
    }
}

package models;

public final class CardImpl implements Card, Comparable<Card> {
    private int cost;
    private int attack;
    private int shield;
    private String name;
    private String resourcePath;
    private String description;

    public CardImpl(final int attack, final int shield, final int cost, final String name, final String resourcePath, final String description) {
        this.cost = cost;
        this.attack = attack;
        this.shield = shield;
        this.name = name;
        this.resourcePath = resourcePath;
        this.description = description;
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
        result = prime * result + shield;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (shield != other.shield) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
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
}

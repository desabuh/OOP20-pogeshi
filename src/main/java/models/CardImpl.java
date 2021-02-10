package models;

public final class CardImpl implements Card, Comparable<Card> {
    private int cost;
    private int attack;
    private int defense;
    private String name;
    private String resourcePath;

    public CardImpl(final int attack, final int defense, final int cost, final String name, final String resourcePath) {
        this.cost = cost;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
        this.resourcePath = resourcePath;
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
    public int getDefense() {
        return this.defense;
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
        result = prime * result + defense;
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
        if (defense != other.defense) {
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
}

package models.battle;

import models.AbstractCard;
import models.Card;
import models.AbstractCard.Builder;

/**
 * A {@link models.Card} implementation.
 */
public final class CardImpl extends AbstractCard implements Comparable<Card> {
    
    public static final class Builder extends AbstractCard.Builder<Builder> {
        
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
            return new CardImpl(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private CardImpl(Builder builder) {
        super(builder);
    }

    @Override
    public int compareTo(final Card card) {
        return this.name.compareTo(card.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + attack;
        result = prime * result + cost;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((resourcePath == null) ? 0 : resourcePath.hashCode());
        result = prime * result + shield;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
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
    public String toString() {
        return "[cost = " + cost + ", attack = " + attack + ", shield = " + shield + ", name = " + name
                + ", resourcePath = " + resourcePath + ", description = " + description + "]";
    }
}

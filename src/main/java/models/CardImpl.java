package models;

/**
 * A {@link models.Card} implementation.
 */
public final class CardImpl extends AbstractCard implements Comparable<Card> {

    public static final class Builder extends AbstractCard.Builder<Builder> {

        /**
         * Returns a newly-created Card.
         *
         * @return A card
         */
        public Card build() {
            return new CardImpl(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private CardImpl(final Builder builder) {
        super(builder);
    }

    @Override
    public int compareTo(final Card card) {
        return this.getName().compareTo(card.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getAttack();
        result = prime * result + this.getCost();
        result = prime * result + ((this.getDescription() == null) ? 0 : this.getDescription().hashCode());
        result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
        result = prime * result + ((this.getResourcePath() == null) ? 0 : this.getResourcePath().hashCode());
        result = prime * result + this.getShield();
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
        if (this.getAttack() != other.getAttack()) {
            return false;
        }
        if (this.getCost() != other.getCost()) {
            return false;
        }
        if (this.getDescription() == null) {
            if (other.getDescription() != null) {
                return false;
            }
        } else if (!this.getDescription().equals(other.getDescription())) {
            return false;
        }
        if (this.getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!this.getName().equals(other.getName())) {
            return false;
        }
        if (this.getResourcePath() == null) {
            if (other.getResourcePath() != null) {
                return false;
            }
        } else if (!this.getResourcePath().equals(other.getResourcePath())) {
            return false;
        }
        if (this.getShield() != other.getShield()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[cost = " + this.getCost() + ", attack = " + this.getAttack() + ", shield = " + this.getShield() + ", name = " + this.getName()
                + ", resourcePath = " + this.getResourcePath() + ", description = " + this.getDescription() + "]";
    }
}

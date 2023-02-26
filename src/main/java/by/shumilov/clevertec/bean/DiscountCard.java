package by.shumilov.clevertec.bean;

/**
 * Class DiscountCard represents discount cards.
 */
public final class DiscountCard extends Item {

    /**
     * Field discountPercentage uses to contain percentage of given discount
     * for a specific discount card.
     */
    private int discountPercentage;

    /**
     * The default constructor has a private access modifier
     * create new objects only through the "builder" pattern.
     */
    private DiscountCard() {
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public static Builder newBuilder() {
        return new DiscountCard().new Builder();
    }

    /**
     * Pattern "builder" uses to create new DiscountCard objects
     * without constructors of DiscountCard class.
     */
    public final class Builder {

        private Builder() {
        }

        public Builder setId(final int id) {
            DiscountCard.this.setId(id);
            return this;
        }

        public Builder setDiscountPercentage(final int newDiscountPercentage) {
            DiscountCard.this.discountPercentage = newDiscountPercentage;
            return this;
        }

        public DiscountCard build() {
            return DiscountCard.this;
        }
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "discountPercentage=" + discountPercentage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountCard that = (DiscountCard) o;

        return discountPercentage == that.discountPercentage;
    }

    @Override
    public int hashCode() {
        return discountPercentage;
    }
}

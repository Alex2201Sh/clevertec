package by.shumilov.clevertec.bean;

/**
 * Class Product represents Products.
 */
public final class Product extends Item {
    private String name;
    private double price;
    /**
     * The field isPromotion shows whether the product is promotional.
     */
    private boolean isPromotion;

    /**
     * The default constructor has a private access modifier
     * create new objects only through the "builder" pattern.
     */
    private Product() {
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public static Product.Builder newBuilder() {
        return new Product().new Builder();
    }

    /**
     * Pattern "builder" uses to create new Product objects
     * without constructors of Product class.
     */
    public final class Builder {

        private Builder() {
        }

        public Product.Builder setId(final int newId) {
            Product.this.setId(newId);
            return this;
        }

        public Product.Builder setName(final String newName) {
            Product.this.name = newName;
            return this;
        }

        public Product.Builder setPrice(final double newPrice) {
            Product.this.price = newPrice;
            return this;
        }

        public Product.Builder setPromotion(final boolean newPromotion) {
            Product.this.isPromotion = newPromotion;
            return this;
        }

        public Product build() {
            return Product.this;
        }
    }


}

package by.shumilov.clevertec.bean;

/**
 * Class ReceiptLine uses for containing lines
 * of Products and its quantities.
 */
public final class ReceiptLine {

    private int quantity;
    private Product product;

    /**
     * The default constructor has a private access modifier
     * create new objects only through the "builder" pattern.
     */
    private ReceiptLine() {
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public static ReceiptLine.Builder newBuilder() {
        return new ReceiptLine().new Builder();
    }

    /**
     * Pattern "builder" uses to create new ReceiptLine objects
     * without constructors of DiscountCard class.
     */
    public final class Builder {

        private Builder() {
            // private constructor
        }

        public ReceiptLine.Builder setQuantity(final int newQuantity) {
            ReceiptLine.this.quantity = newQuantity;
            return this;
        }

        public ReceiptLine.Builder setProduct(final Product newProduct) {
            ReceiptLine.this.product = newProduct;
            return this;
        }

        public ReceiptLine build() {
            return ReceiptLine.this;
        }
    }

}

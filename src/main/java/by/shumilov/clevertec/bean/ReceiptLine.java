package by.shumilov.clevertec.bean;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiptLine that = (ReceiptLine) o;

        if (quantity != that.quantity) return false;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReceiptLine{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}

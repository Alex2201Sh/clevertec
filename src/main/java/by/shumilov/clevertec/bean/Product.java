package by.shumilov.clevertec.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Class Product represents Products.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "set")
public final class Product extends Item {
    private String name;
    private double price;
    /**
     * The field isPromotion shows whether the product is promotional.
     */
    private boolean promotion;

    /**
     * The default constructor has a private access modifier
     * create new objects only through the "builder" pattern.
     */
//    private Product() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public boolean isPromotion() {
//        return promotion;
//    }
//
//    public static Product.Builder newBuilder() {
//        return new Product().new Builder();
//    }
//
//    /**
//     * Pattern "builder" uses to create new Product objects
//     * without constructors of Product class.
//     */
//    public final class Builder {
//
//        private Builder() {
//        }
//
//        public Product.Builder setId(final int newId) {
//            Product.this.setId(newId);
//            return this;
//        }
//
//        public Product.Builder setName(final String newName) {
//            Product.this.name = newName;
//            return this;
//        }
//
//        public Product.Builder setPrice(final double newPrice) {
//            Product.this.price = newPrice;
//            return this;
//        }
//
//        public Product.Builder setPromotion(final boolean newPromotion) {
//            Product.this.promotion = newPromotion;
//            return this;
//        }
//
//        public Product build() {
//            return Product.this;
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Product product = (Product) o;
//
//        if (Double.compare(product.price, price) != 0) return false;
//        if (promotion != product.promotion) return false;
//        return Objects.equals(name, product.name);
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = name != null ? name.hashCode() : 0;
//        temp = Double.doubleToLongBits(price);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + (promotion ? 1 : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "name='" + name + '\'' +
//                ", price=" + price +
//                ", isPromotion=" + promotion +
//                '}';
//    }
}

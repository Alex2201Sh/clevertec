package by.shumilov.clevertec.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Class Product represents Products.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(setterPrefix = "set")
@Jacksonized
public final class Product extends Item {
    private String name;
    private double price;
    /**
     * The field isPromotion shows whether the product is promotional.
     */
    private boolean promotion;
}

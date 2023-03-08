package by.shumilov.clevertec.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Class DiscountCard represents discount cards.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(setterPrefix = "set")
@Jacksonized
public final class DiscountCard extends Item {

    /**
     * Field discountPercentage uses to contain percentage of given discount
     * for a specific discount card.
     */
    private int discountPercentage;
}



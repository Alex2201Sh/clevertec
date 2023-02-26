package by.shumilov.clevertec.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class DiscountCardTest {

    private final DiscountCard discountCardW10Percent = DiscountCard.newBuilder().setDiscountPercentage(10).build();

    @Test
    void getDiscountPercentage() {
        assertEquals(10, discountCardW10Percent.getDiscountPercentage());
    }

}
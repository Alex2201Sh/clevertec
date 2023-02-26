package by.shumilov.clevertec.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {

    private final Product product = Product.newBuilder()
            .setId(1)
            .setName("name")
            .setPrice(1.11)
            .setPromotion(true)
            .build();

    @Test
    void getName() {
        assertEquals(1, product.getId());
    }

    @Test
    void getPrice() {
        assertEquals("name", product.getName());
    }

    @Test
    void isPromotion() {
        assertTrue(product.isPromotion());
    }
}
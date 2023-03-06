package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReceiptCalculatorTest {

    private final Receipt receipt = new Receipt();
    private final ReceiptCalculator calculator = new ReceiptCalculator(receipt);

    @BeforeEach
    void init() {
        receipt.setDiscountCard(DiscountCard.newBuilder().setDiscountPercentage(10).setId(1).build());
        Product product1 = Product.newBuilder().setPrice(1.11).build();
        Product product2 = Product.newBuilder().setPrice(2.22).build();
        List<ReceiptLine> receiptLineList = new ArrayList<>();
        ReceiptLine receiptLine1 = ReceiptLine.newBuilder().setProduct(product1).setQuantity(1).build();
        ReceiptLine receiptLine2 = ReceiptLine.newBuilder().setProduct(product2).setQuantity(2).build();
        receiptLineList.add(receiptLine1);
        receiptLineList.add(receiptLine2);
        receipt.setReceiptLineList(receiptLineList);
    }

    @Test
    void getTotalCost() {
        double actualTotalCost = calculator.getTotalCost();
        double expectedTotalCost = 5.55;
        assertThat(actualTotalCost).isCloseTo(expectedTotalCost, Percentage.withPercentage(0.1));
    }

    @Test
    void getTotalCostWithDiscount() {
        double actualTotalCostWithDiscount = calculator.getTotalCostWithDiscount();
        double expectedTotalCostWithDiscount = 5.00;
        assertThat(actualTotalCostWithDiscount)
                .isCloseTo(expectedTotalCostWithDiscount, Percentage.withPercentage(0.1));
    }
}
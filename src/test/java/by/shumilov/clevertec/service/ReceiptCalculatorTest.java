package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.Receipt;
import org.junit.jupiter.api.Test;

import static by.shumilov.clevertec.test_builder.TestBuilder.receiptBuilder;
import static org.assertj.core.api.Assertions.assertThat;

class ReceiptCalculatorTest {

    private final Receipt receipt = receiptBuilder().build();
    private final ReceiptCalculator calculator = new ReceiptCalculator(receipt);

    @Test
    void getTotalCost() {
        double actualTotalCost = calculator.getTotalCost();
        assertThat(actualTotalCost).isGreaterThan(1);
    }

    @Test
    void getTotalCostWithDiscount() {
        double actualTotalCostWithDiscount = calculator.getTotalCostWithDiscount();
        assertThat(actualTotalCostWithDiscount).isGreaterThan(1);
    }
}
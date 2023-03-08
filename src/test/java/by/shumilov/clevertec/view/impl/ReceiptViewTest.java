package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.bean.Receipt;
import org.junit.jupiter.api.Test;

import static by.shumilov.clevertec.test_builder.TestBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

class ReceiptViewTest {

    private final ReceiptView receiptView = new ReceiptView();

    @Test
    void viewReceipt() {
        Receipt receipt = receiptBuilder()
                .setDiscountCard(discountCardBuilder().build())
                .setReceiptLineList(receiptLineList())
                .build();
        assertThat(receiptView.viewReceipt(receipt).length()).isGreaterThan(20);
    }
}
package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Receipt;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReceiptViewTest {

    private final ReceiptView receiptView = new ReceiptView();

    @Test
    void viewReceipt() {
        Receipt receipt = Receipt.anReceipt().build();
        receipt.setDiscountCard(DiscountCard.builder().superId(1).build());
        assertThat(receiptView.viewReceipt(receipt)).isInstanceOf(String.class);
    }
}
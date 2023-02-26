package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Receipt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptViewTest {

    private final ReceiptView receiptView = new ReceiptView();

    @Test
    void viewReceipt() {
        Receipt receipt = new Receipt();
        receipt.setDiscountCard(DiscountCard.newBuilder().setId(1).build());
        assertEquals(String.class, receiptView.viewReceipt(receipt).getClass());
    }
}
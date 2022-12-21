package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptCreatorTest {

    private ReceiptCreator receiptCreator = new ReceiptCreator();

    @Test
    void addLineToReceipt() {
        int initSize = receiptCreator.getReceipt().getReceiptLineList().size();
        receiptCreator.addLineToReceipt(ReceiptLine.newBuilder().build());
        int resultSize = receiptCreator.getReceipt().getReceiptLineList().size();
        assertEquals(initSize + 1, resultSize);
    }

    @Test
    void getReceipt() {
        assertEquals(Receipt.class, receiptCreator.getReceipt().getClass());
    }
}
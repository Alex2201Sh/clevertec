package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReceiptCreatorTest {

    private final ReceiptCreator receiptCreator = new ReceiptCreator();

    @Test
    void addLineToReceipt() {
        int initSize = receiptCreator.getReceipt().getReceiptLineList().size();
        receiptCreator.addLineToReceipt(ReceiptLine.newBuilder().build());
        int resultSize = receiptCreator.getReceipt().getReceiptLineList().size();
        assertThat(resultSize).isEqualTo(initSize + 1);
    }

    @Test
    void getReceipt() {
        assertThat(receiptCreator.getReceipt()).isInstanceOf(Receipt.class);
    }
}
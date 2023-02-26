package by.shumilov.clevertec.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptTest {

    private ReceiptLine rl1 = ReceiptLine.newBuilder().build();
    private ReceiptLine rl2 = ReceiptLine.newBuilder().build();
    private List<ReceiptLine> receiptLineList = new ArrayList<>();
    private DiscountCard discountCard = DiscountCard.newBuilder().build();
    private Receipt receipt = new Receipt();

    @BeforeEach
    void init() {
        receipt.setDiscountCard(discountCard);
        receiptLineList.add(rl1);
        receiptLineList.add(rl2);
        receipt.setReceiptLineList(receiptLineList);
    }

    @Test
    void getDiscountCard() {
        assertEquals(discountCard, receipt.getDiscountCard());
    }

    @Test
    void getReceiptLineList() {
        assertEquals(receiptLineList, receipt.getReceiptLineList());
    }

}
package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;

/**
 * Class is used to create one receipt from array of lines,
 * calculate its total cost and total cost with discount.
 */
public class ReceiptCreator {

    private final Receipt receipt = new Receipt();

    public void addLineToReceipt(ReceiptLine receiptLine) {
        receipt.getReceiptLineList().add(receiptLine);
    }

    public Receipt getReceipt() {
        return receipt;
    }
}

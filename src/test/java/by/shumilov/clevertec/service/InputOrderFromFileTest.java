package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.view.impl.TextFileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InputOrderFromFileTest {

    private final TextFileReader textFileReader = new TextFileReader();
    private final InputOrderFromFile inputOrderFromFile = new InputOrderFromFile();

    @Test
    void inputOrder() {
        Receipt receipt = inputOrderFromFile.inputOrder(new String[]{"inputProducts", "inputDiscountCards", "smallDataInput"});
        assertTrue(receipt.getReceiptLineList().size()>0);
    }
}
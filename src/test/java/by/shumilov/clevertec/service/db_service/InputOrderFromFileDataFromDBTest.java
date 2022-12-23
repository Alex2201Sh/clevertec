package by.shumilov.clevertec.service.db_service;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.view.impl.TextFileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InputOrderFromFileDataFromDBTest {

    private final TextFileReader textFileReader = new TextFileReader();
    private final InputOrderFromFileDataFromDB inputOrder = new InputOrderFromFileDataFromDB();

    @Test
    void inputOrder() {
        Receipt receipt = inputOrder.inputOrder(new String[]{"db", "smallDataInput"});
        assertTrue(receipt.getReceiptLineList().size() > 0);
    }
}
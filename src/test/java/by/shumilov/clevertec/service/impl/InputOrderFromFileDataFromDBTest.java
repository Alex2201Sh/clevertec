package by.shumilov.clevertec.service.impl;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.service.impl.InputOrderFromFileDataFromDB;
import by.shumilov.clevertec.view.impl.TextFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InputOrderFromFileDataFromDBTest {

    private final TextFileReader textFileReader = new TextFileReader();
    private final InputOrderFromFileDataFromDB inputOrder = new InputOrderFromFileDataFromDB(textFileReader);

    @Test
    void inputOrder() {
        Receipt receipt = inputOrder.inputOrder(new String[]{"db", "src/test/resources/inputData/receiptValid.txt"});
        assertTrue(receipt.getReceiptLineList().size() > 0);
    }
}
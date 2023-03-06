package by.shumilov.clevertec.service.impl;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.view.impl.TextFileReader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class InputOrderFromFileDataFromDBTest {

    private final TextFileReader textFileReader = new TextFileReader();
    private final InputOrderFromFileDataFromDB inputOrder = new InputOrderFromFileDataFromDB(textFileReader);

    @Test
    void inputOrder() {
        Receipt receipt = inputOrder.inputOrder(new String[]{"db", "src/test/resources/inputData/receiptValid.txt"});
        Assertions.assertThat(receipt.getReceiptLineList().size() > 0).isTrue();
    }
}
package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Receipt;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;


class TextFileWriterTest {

    private final TextFileWriter writer = new TextFileWriter();

    @Test
    void writeReceiptToFile() {
        Receipt receipt = new Receipt();
        receipt.setDiscountCard(DiscountCard.newBuilder().setId(1).build());
        writer.writeReceiptToFile(receipt, "src/main/resources/outputData/receipt.txt");

        String filePath = "src/main/resources/outputData/receipt.txt";
        File file = new File(filePath);

        assertTrue(file.exists() && !file.isDirectory());
    }
}
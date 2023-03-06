package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Receipt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


class TextFileWriterTest {

    private final TextFileWriter writer = new TextFileWriter();
    private final String filePath = "src/test/resources/testReceipt.txt";


    @Test
    void writeReceiptToFile() {
        Receipt receipt = new Receipt();
        receipt.setDiscountCard(DiscountCard.newBuilder().setId(1).build());
        writer.writeReceiptToFile(receipt, filePath);
        File file = new File(filePath);
        assertThat(file.exists() && !file.isDirectory()).isTrue();
    }

    @AfterEach
    void deleteFile() {
        new File(filePath).delete();
    }
}
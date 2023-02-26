package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Receipt;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;


class TextFileWriterTest {

    private final TextFileWriter writer = new TextFileWriter();

    @Test
    void writeReceiptToFile() {
        Receipt receipt = new Receipt();
        receipt.setDiscountCard(DiscountCard.newBuilder().setId(1).build());
        writer.writeReceiptToFile(receipt, "src/test/resources/receipt.txt");

        String filePath = "src/test/resources/receipt.txt";
        File file = new File(filePath);

        Assertions.assertThat(file.exists() && !file.isDirectory()).isTrue();
    }
}
package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.view.Writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class TextFileWriter uses to write receipt to file
 * in resources/outputData folder.
 */
public class TextFileWriter implements Writer {

    public void writeReceiptToFile(Receipt receipt, String filename) {

        try (FileWriter writer = new FileWriter(filename, false)) {
            ReceiptView receiptView = new ReceiptView();
            String receiptToString = receiptView.viewReceipt(receipt);
            writer.write(receiptToString);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

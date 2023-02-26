package by.shumilov.clevertec.controller;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.service.impl.InputOrderFromFile;
import by.shumilov.clevertec.service.impl.InputOrderFromFileDataFromDB;
import by.shumilov.clevertec.view.impl.ReceiptView;
import by.shumilov.clevertec.view.impl.TextFileReader;
import by.shumilov.clevertec.view.impl.TextFileWriter;

public class Runner {

    public static void main(String[] args) {
        ReceiptView receiptView = new ReceiptView();
        TextFileWriter textFileWriter = new TextFileWriter();
        TextFileReader textFileReader = new TextFileReader();
        Receipt receipt = null;

        if (args.length == 3) {
            InputOrderFromFile inputOrderFromFile = new InputOrderFromFile(textFileReader);
            receipt = inputOrderFromFile.inputOrder(args);
        } else if (args.length == 2) {
            InputOrderFromFileDataFromDB inputOrderFromFileDataFromDB
                    = new InputOrderFromFileDataFromDB(textFileReader);
            receipt = inputOrderFromFileDataFromDB.inputOrder(args);
        }

        assert receipt != null;
        receiptView.outputToConsole(receiptView.viewReceipt(receipt));
        textFileWriter.writeReceiptToFile(receipt, "src/main/resources/outputData/receipt.txt");
    }
}

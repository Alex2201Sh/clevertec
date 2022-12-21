package by.shumilov.clevertec.controller;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.service.InputOrderFromFile;
import by.shumilov.clevertec.service.db_service.InputOrderFromFileDataFromDB;
import by.shumilov.clevertec.view.impl.ReceiptView;
import by.shumilov.clevertec.view.impl.TextFileWriter;

public class Runner {

    //Args for testing: "inputProducts", "inputDiscountCards", "smallDataInput"
    //Args for testing with database: "db", "smallDataInput"
    public static void main(String[] args) {
        ReceiptView receiptView = new ReceiptView();
        TextFileWriter textFileWriter = new TextFileWriter();
        Receipt receipt = null;

        if (args.length == 3) {
            InputOrderFromFile inputOrderFromFile = new InputOrderFromFile();
            receipt = inputOrderFromFile.inputOrder(args);
        } else if (args.length == 2) {
            InputOrderFromFileDataFromDB inputOrderFromFileDataFromDB
                    = new InputOrderFromFileDataFromDB();
            receipt = inputOrderFromFileDataFromDB.inputOrder(args);
        }

        assert receipt != null;
        receiptView.outputToConsole(receiptView.viewReceipt(receipt));
        textFileWriter.writeReceiptToFile(receipt, "src/main/resources/outputData/receipt.txt");
    }
}

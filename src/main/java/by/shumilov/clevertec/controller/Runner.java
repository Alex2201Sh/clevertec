package by.shumilov.clevertec.controller;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.service.InputOrderFromFile;
import by.shumilov.clevertec.view.impl.ReceiptView;
import by.shumilov.clevertec.view.impl.TextFileWriter;

public class Runner {

    //Args for testing: "inputProducts", "inputDiscountCards", "smallDataInput"
    public static void main(String[] args) {
        ReceiptView receiptView = new ReceiptView();
        TextFileWriter textFileWriter = new TextFileWriter();
        InputOrderFromFile inputOrderFromFile = new InputOrderFromFile();
        Receipt receipt = inputOrderFromFile.inputOrder(args);
        receiptView.outputToConsole(receiptView.viewReceipt(receipt));
        textFileWriter.writeReceiptToFile(receipt);
    }
}

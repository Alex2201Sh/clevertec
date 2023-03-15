package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.service.impl.InputOrderFromFileDataFromDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputReceiptService {

    private final InputOrderFromFileDataFromDB inputOrderService;

    @Autowired
    public InputReceiptService(InputOrderFromFileDataFromDB inputOrderService) {
        this.inputOrderService = inputOrderService;
    }

    public Receipt getReceipt() {
        return inputOrderService.inputOrder(new String[]{"db", "src/test/resources/inputData/receiptValid.txt"});
    }
}

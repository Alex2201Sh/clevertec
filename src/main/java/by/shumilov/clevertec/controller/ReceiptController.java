package by.shumilov.clevertec.controller;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.service.InputReceiptService;
import by.shumilov.clevertec.service.ReceiptCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final InputReceiptService service;

    @Autowired
    public ReceiptController(InputReceiptService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Receipt> getAllReceipts() {
        return Collections.singletonList(service.getReceipt());
    }

    @GetMapping("/calculate")
    public String calculateReceipt() {
        Receipt receipt = service.getReceipt();
        ReceiptCalculator calculator = new ReceiptCalculator(receipt);
        return "Total cost: " + calculator.getTotalCost() +
                "<br>" +
                "Total cost with discount: " + calculator.getTotalCostWithDiscount();
    }

}

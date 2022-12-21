package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;
import by.shumilov.clevertec.service.ReceiptCalculator;
import by.shumilov.clevertec.view.View;

import java.util.List;

/**
 * Class ReceiptView creates String value of total Receipt
 * with all necessary information for subsequent output.
 */
public class ReceiptView implements View {

    private final ReceiptCalculator receiptCalculator = new ReceiptCalculator();

    public String viewReceipt(Receipt receipt) {
        StringBuilder resultReceipt = new StringBuilder();
        List<ReceiptLine> receiptLineList = receipt.getReceiptLineList();


        resultReceipt       //Create header of receipt.
                .append("Discount card №")
                .append(receipt.getDiscountCard().getId())
                .append("\n")
                .append("Current discount percentage: ")
                .append(receipt.getDiscountCard().getDiscountPercentage())
                .append("\n")
                .append(String.format("|%s|", "Qty"))
                .append(String.format("%21s|", "Product"))
                .append(String.format("%7s|", "Price"))
                .append(String.format("%7s|", "Total"))
                .append("\n");

        for (ReceiptLine receiptLine : receiptLineList) {   //Create lines with products.
            resultReceipt
                    .append(String.format("|%3d| ", receiptLine.getQuantity()))
                    .append(String.format("%20s| ", receiptLine.getProduct().getName()))
                    .append(String.format("%6.2f| ", receiptLine.getProduct().getPrice()))
                    .append(String.format("%6.2f|", (receiptLine.getProduct().getPrice() * receiptLine.getQuantity())))
                    .append("\n");
            if (receiptLine.getProduct().isPromotion() && receiptLine.getQuantity() >= 5) {
                resultReceipt
                        .append(String.format("|%-41s|", "Additional promotion 10% for line above."))
                        .append("\n");
            }
        }

        resultReceipt           //Create footer of receipt.
                .append(String.format("|%-36s", "Total cost:"))
                .append(String.format("%.2f|", receiptCalculator.getTotalCost(receipt)))
                .append("\n")
                .append(String.format("|%-36s", "Total cost with discount:"))
                .append(String.format("%.2f|", receiptCalculator.getTotalCostWithDiscount(receipt)))
                .append("\n");

        return resultReceipt.toString();
    }

    public void outputToConsole(String arg) {
        System.out.println(arg);
    }

}

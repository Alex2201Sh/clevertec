package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;

import java.util.List;

/**
 * Class is used to create one receipt from array of lines,
 * calculate its total cost and total cost with discount.
 */
public class ReceiptCalculator {

    /**
     * The method calculates total cost of Receipt.
     *
     * @param receipt - given Receipt
     * @return - total Receipt cost without discount.
     */
    public double getTotalCost(Receipt receipt) {
        List<ReceiptLine> receiptLineList = receipt.getReceiptLineList();
        double totalCost = 0;
        for (ReceiptLine receiptLine : receiptLineList) {
            double lineCost;
            //if product has a promotion, method make additional 10% discount.
            if (receiptLine.getProduct().isPromotion() && receiptLine.getQuantity() >= 5) {
                lineCost = receiptLine.getQuantity() *
                        receiptLine.getProduct().getPrice() * 0.9;
            } else {
                lineCost = receiptLine.getQuantity() *
                        receiptLine.getProduct().getPrice();
            }
            totalCost += lineCost;
        }

        return totalCost;
    }


    /**
     * The method calculates total cost of Receipt with discount.
     *
     * @param receipt - given Receipt
     * @return - total Receipt cost with discount.
     */
    public double getTotalCostWithDiscount(Receipt receipt) {
        return getTotalCost(receipt) * (100 - receipt.getDiscountCard().getDiscountPercentage()) / 100;
    }

}

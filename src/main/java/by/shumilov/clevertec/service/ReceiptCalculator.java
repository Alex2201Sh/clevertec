package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.Receipt;

/**
 * Class is used to create one receipt from array of lines,
 * calculate its total cost and total cost with discount.
 */
public class ReceiptCalculator {

    private Receipt receipt;

    public ReceiptCalculator(Receipt receipt) {
        this.receipt = receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    /**
     * The method calculates total cost of Receipt.
     *
     * @return - total Receipt cost without discount.
     */
    public double getTotalCost() {
        return receipt.getReceiptLineList().stream()
                .mapToDouble(receiptLine -> receiptLine.getProduct().isPromotion() && receiptLine.getQuantity() >= 5 ?
                        receiptLine.getQuantity() * receiptLine.getProduct().getPrice() * 0.9 :
                        receiptLine.getQuantity() * receiptLine.getProduct().getPrice())
                .sum();
    }


    /**
     * The method calculates total cost of Receipt with discount.
     *
     * @return - total Receipt cost with discount.
     */
    public double getTotalCostWithDiscount() {
        return getTotalCost()
                * (100 - receipt.getDiscountCard().getDiscountPercentage())
                / 100;
    }

}

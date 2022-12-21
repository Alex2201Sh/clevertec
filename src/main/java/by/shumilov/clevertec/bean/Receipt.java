package by.shumilov.clevertec.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Class receipt uses for containing List of ReceiptLine objects
 * and only one DiscountCard object.
 */
public class Receipt {

    private DiscountCard discountCard;
    private List<ReceiptLine> receiptLineList = new ArrayList<>();

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(final DiscountCard newDiscountCard) {
        this.discountCard = newDiscountCard;
    }

    public List<ReceiptLine> getReceiptLineList() {
        return receiptLineList;
    }

    public void setReceiptLineList(final List<ReceiptLine> newReceiptLineList) {
        this.receiptLineList = newReceiptLineList;
    }

}

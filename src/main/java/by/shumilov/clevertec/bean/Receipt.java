package by.shumilov.clevertec.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if (!Objects.equals(discountCard, receipt.discountCard))
            return false;
        return Objects.equals(receiptLineList, receipt.receiptLineList);
    }

    @Override
    public int hashCode() {
        int result = discountCard != null ? discountCard.hashCode() : 0;
        result = 31 * result + (receiptLineList != null ? receiptLineList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "discountCard=" + discountCard +
                ", receiptLineList=" + receiptLineList +
                '}';
    }
}

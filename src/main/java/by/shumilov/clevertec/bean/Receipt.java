package by.shumilov.clevertec.bean;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Class receipt uses for containing List of ReceiptLine objects
 * and only one DiscountCard object.
 */
@Data
@Builder(builderMethodName = "anReceipt", toBuilder = true, setterPrefix = "set")
@Jacksonized
public class Receipt {

    private DiscountCard discountCard;
    private List<ReceiptLine> receiptLineList;
}

package by.shumilov.clevertec.bean;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * Class ReceiptLine uses for containing lines
 * of Products and its quantities.
 */
@Data
@Builder(builderMethodName = "anReceiptLine", toBuilder = true, setterPrefix = "set")
@Jacksonized
public final class ReceiptLine {

    private int quantity;
    private Product product;
}

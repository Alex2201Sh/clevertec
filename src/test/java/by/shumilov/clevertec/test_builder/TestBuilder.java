package by.shumilov.clevertec.test_builder;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;

import java.util.Arrays;
import java.util.List;

public class TestBuilder {
    public static Receipt.ReceiptBuilder receiptBuilder() {
        return Receipt.anReceipt()
                .setDiscountCard(discountCardBuilder()
                        .build())
                .setReceiptLineList(receiptLineList());
    }

    public static DiscountCard.DiscountCardBuilder discountCardBuilder() {
        return DiscountCard.builder();
    }

    public static List<ReceiptLine> receiptLineList() {
        return Arrays.asList(
                receiptLineBuilder().setQuantity(2).build(),
                receiptLineBuilder().setQuantity(10).build());
    }

    public static ReceiptLine.ReceiptLineBuilder receiptLineBuilder() {
        return ReceiptLine.anReceiptLine()
                .setProduct(productBuilder().build())
                .setQuantity(5);
    }

    public static Product.ProductBuilder productBuilder() {
        return Product.builder()
                .setName("Product")
                .setPrice(1.23)
                .setPromotion(true);
    }
}

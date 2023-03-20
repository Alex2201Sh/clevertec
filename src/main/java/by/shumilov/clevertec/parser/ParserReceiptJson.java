package by.shumilov.clevertec.parser;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParserReceiptJson implements Parser{

    @Override
    public Receipt deSerialize(String json) {
        Receipt receipt = Receipt.anReceipt().build();


        int discountCardIndex = json.indexOf("discountCard") + "discountCard".length();
        int discountCardIdIndex = json.indexOf("id", discountCardIndex) + 4;
        int discountCardId = Integer.parseInt(json.substring(discountCardIdIndex, json.indexOf(",", discountCardIdIndex)));
        int discountPercentageIndex = json.indexOf("discountPercentage") + "discountPercentage".length() + 2;
        int discountPercentage = Integer.parseInt(json.substring(discountPercentageIndex, json.indexOf("}", discountPercentageIndex)));

        receipt.setDiscountCard(DiscountCard.builder()
                .superId(discountCardId)
                .setDiscountPercentage(discountPercentage)
                .build());

        int startIndex = json.indexOf("receiptLineList") + "receiptLineList".length();
        List<ReceiptLine> receiptLineList = new ArrayList<>();

        while (json.indexOf("quantity", startIndex) > 0) {
            int quantityIndex = json.indexOf("quantity", startIndex) + "quantity".length() + 2;
            int quantity = Integer.parseInt(json.substring(quantityIndex, json.indexOf(",", quantityIndex)));
            startIndex = quantityIndex + 1;

            int productIndex = json.indexOf("product", quantityIndex) + "product".length();

            int productIdIndex = json.indexOf("id", productIndex) + "id".length() + 2;
            int productId = Integer.parseInt(json.substring(productIdIndex, json.indexOf(",", productIdIndex)));

            int nameIndex = json.indexOf("name", productIdIndex) + "name".length() + 2;
            String productName = json.substring(nameIndex, json.indexOf(",", nameIndex)).replace("\"", "");
            productName = !"null".equals(productName) ? productName : null;

            int productPriceIndex = json.indexOf("price", nameIndex) + "price".length() + 2;
            double productPrice = Double.parseDouble(json.substring(productPriceIndex, json.indexOf(",", productPriceIndex)));

            int promotionIndex = json.indexOf("promotion", productPriceIndex) + "promotion".length() + 2;
            boolean productPromotion = Boolean.parseBoolean(json.substring(promotionIndex, json.indexOf("}", promotionIndex)));

            Product product = Product.builder()
                    .superId(productId)
                    .setName(productName)
                    .setPrice(productPrice)
                    .setPromotion(productPromotion)
                    .build();
            ReceiptLine receiptLine = ReceiptLine.anReceiptLine()
                    .setQuantity(quantity)
                    .setProduct(product)
                    .build();
            receiptLineList.add(receiptLine);
        }
        receipt.setReceiptLineList(receiptLineList);
        return receipt;
    }

    @Override
    public String serialize(Object object) throws RuntimeException {

        StringBuilder result = new StringBuilder();

        Field[] declaredFields = object.getClass().getDeclaredFields();

        declaredFields[0].setAccessible(true);
        declaredFields[1].setAccessible(true);

        DiscountCard discountCard = null;
        List<ReceiptLine> receiptLineList = null;

        try {
            discountCard = (DiscountCard) declaredFields[0].get(object);
            receiptLineList = (List<ReceiptLine>) declaredFields[1].get(object);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int discountCardId = Objects.requireNonNull(discountCard).getId();
        int discountPercentage = discountCard.getDiscountPercentage();

        result.append("{\"discountCard\":{").append("\"id\":").append(discountCardId).append(",")
                .append("\"discountPercentage\":").append(discountPercentage)
                .append("},");

        ReceiptLine receiptLineLastLine = Objects.requireNonNull(receiptLineList).get(receiptLineList.size() - 1);
        result.append("\"receiptLineList\":[");
        receiptLineList
                .forEach(receiptLine -> {
                            Product product = receiptLine.getProduct();
                            String name = product.getName() != null ? "\"" + product.getName() + "\"" : null;
                            String last = !receiptLineLastLine.equals(receiptLine) ? "}}," : "}}";
                            result
                                    .append("{")
                                    .append("\"quantity\":").append(receiptLine.getQuantity()).append(",")
                                    .append("\"product\":{")
                                    .append("\"id\":").append(product.getId()).append(",")
                                    .append("\"name\":").append(name).append(",")
                                    .append("\"price\":").append(product.getPrice()).append(",")
                                    .append("\"promotion\":").append(product.getPromotion()).append(last);
                        }
                );
        result.append("]}");
        return String.valueOf(result);
    }
}

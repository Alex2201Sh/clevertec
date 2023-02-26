package by.shumilov.clevertec.parser;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class ParserReceiptJsonTest {

    private final ParserReceiptJson parser = new ParserReceiptJson();
    private final Receipt receipt = new Receipt();

    @BeforeEach
    void init() {
        receipt.setDiscountCard(DiscountCard.newBuilder().setDiscountPercentage(10).setId(1).build());
        Product product1 = Product.newBuilder().setPrice(1.11).setName("abc").setPromotion(true).build();
        Product product2 = Product.newBuilder().setPrice(2.22).setPromotion(false).build();
        Product product3 = Product.newBuilder().setPrice(3.33).setPromotion(false).build();
        List<ReceiptLine> receiptLineList = new ArrayList<>();
        receiptLineList.add(ReceiptLine.newBuilder().setProduct(product1).setQuantity(1).build());
        receiptLineList.add(ReceiptLine.newBuilder().setProduct(product2).setQuantity(2).build());
        receiptLineList.add(ReceiptLine.newBuilder().setProduct(product3).setQuantity(5).build());
        receipt.setReceiptLineList(receiptLineList);
    }

    @Test
    void deSerializeCheck() {
        String json = "{\"discountCard\":{\"id\":1,\"discountPercentage\":10}," +
                "\"receiptLineList\":" +
                "[{\"quantity\":1,\"product\":{\"id\":0,\"name\":\"abc\",\"price\":1.11,\"promotion\":true}}," +
                "{\"quantity\":2,\"product\":{\"id\":0,\"name\":null,\"price\":2.22,\"promotion\":false}}," +
                "{\"quantity\":5,\"product\":{\"id\":0,\"name\":\"abc\",\"price\":1.11,\"promotion\":true}}]}";
        Receipt actualReceipt = parser.deSerialize(json);
        Receipt expectedReceipt = getReceipt(json);
        Assertions.assertThat(actualReceipt).isEqualTo(expectedReceipt);
    }

    @Test
    void serializeCheck() {
        String actualResult = parser.serialize(receipt);
        String expectedResult = mapperWrite(receipt);
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }

    private static Receipt getReceipt(String newJson) {
        try {
            return newMapper().readValue(newJson, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String mapperWrite(Object object) {
        try {
            return newMapper().writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package by.shumilov.clevertec.parser;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Receipt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Locale;

import static by.shumilov.clevertec.test_builder.TestBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParserReceiptJsonTest {

    private final ParserReceiptJson parser = new ParserReceiptJson();
    @Test
    void deSerializeCheck() {
        String json = "{\"discountCard\":{\"id\":1,\"discountPercentage\":10}," +
                "\"receiptLineList\":" +
                "[{\"quantity\":1,\"product\":{\"id\":2,\"name\":\"abc\",\"price\":1.11,\"promotion\":true}}," +
                "{\"quantity\":2,\"product\":{\"id\":3,\"name\":null,\"price\":2.22,\"promotion\":false}}," +
                "{\"quantity\":5,\"product\":{\"id\":4,\"name\":\"abc\",\"price\":1.11,\"promotion\":true}}]}";
        Receipt actualReceipt = parser.deSerialize(json);
        Receipt expectedReceipt = getReceipt(json);
        assertThat(actualReceipt).isEqualTo(expectedReceipt);
    }

    @Test
    void serializeCheck() {
        Receipt receipt = receiptBuilder()
                .setDiscountCard((DiscountCard) discountCardBuilder().setDiscountPercentage(10).superId(1).build())
                .setReceiptLineList(receiptLineList())
                .build();
        String actualResult = parser.serialize(receipt);
        String expectedResult = mapperWrite(receipt);
        assertThat(actualResult).isEqualTo(expectedResult);
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
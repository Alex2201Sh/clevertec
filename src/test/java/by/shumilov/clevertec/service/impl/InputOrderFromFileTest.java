package by.shumilov.clevertec.service.impl;

import by.shumilov.clevertec.view.impl.TextFileReader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class InputOrderFromFileTest {

    private final TextFileReader textFileReader = new TextFileReader();
    private final InputOrderFromFile inputOrder = new InputOrderFromFile(textFileReader);

    @ParameterizedTest
    @MethodSource("getArguments")
    void inputOrder(String productsSource, String discountCardsSource, String receiptSource, boolean expected) {
        try {
            inputOrder.inputOrder(new String[]
                    {productsSource, discountCardsSource, receiptSource});
        } catch (ArrayIndexOutOfBoundsException e) {
            Assertions.assertThat(expected).isFalse();
        } catch (Exception e){
            Assertions.assertThat(expected).isFalse();
        }
    }

    static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of("src/test/resources/inputData/inputProductsValid.txt",
                        "src/test/resources/inputData/inputDiscountCardsTestValid.txt",
                        "src/test/resources/inputData/receiptValid.txt",
                        true),
                Arguments.of("src/test/resources/inputData/inputProductsInvalid.txt",
                        "src/test/resources/inputData/inputDiscountCardsTestValid.txt",
                        "src/test/resources/inputData/receiptValid.txt",
                        false),
                Arguments.of("src/test/resources/inputData/inputProductsInvalid.txt",
                        "src/test/resources/inputData/inputDiscountCardsTestInvalid.txt",
                        "src/test/resources/inputData/receiptValid.txt",
                        false)
        );
    }
}
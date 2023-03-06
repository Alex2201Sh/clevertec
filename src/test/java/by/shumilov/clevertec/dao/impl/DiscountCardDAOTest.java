package by.shumilov.clevertec.dao.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.dao.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class DiscountCardDAOTest {

    DiscountCardDAO discountCardDAO = new DiscountCardDAO();
    private final List<DiscountCard> discountCardList = new ArrayList<>();

    static Stream<Arguments> getArguments() {
        DiscountCard card1 = DiscountCard.newBuilder().setId(1).build();
        DiscountCard card2 = DiscountCard.newBuilder().setId(2).build();
        return Stream.of(
                Arguments.of(1, card1),
                Arguments.of(2, card2)
        );
    }

    @BeforeEach
    void init() {
        DiscountCard card1 = DiscountCard.newBuilder().setId(1).build();
        DiscountCard card2 = DiscountCard.newBuilder().setId(2).build();
        discountCardDAO.addDiscountCardToList(card1);
        discountCardDAO.addDiscountCardToList(card2);
        discountCardList.add(card1);
        discountCardList.add(card2);
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void findById(int id, DiscountCard card) throws DaoException {
        assertThat(discountCardDAO.findById(id)).isEqualTo(card);
    }

    @Test
    void addDiscountCardToList() {
        int initSize = discountCardDAO.getDiscountCardList().size();
        discountCardDAO.addDiscountCardToList(DiscountCard.newBuilder().setId(100).build());
        int resultSize = discountCardDAO.getDiscountCardList().size();
        assertThat(resultSize).isEqualTo(initSize + 1);
    }

    @Test
    void getDiscountCardList() {
        assertThat(discountCardDAO
                .getDiscountCardList())
                .isEqualTo(discountCardList);
    }
}
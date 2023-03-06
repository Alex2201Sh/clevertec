package by.shumilov.clevertec.dao.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.dao.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class DiscountCardDAOTest {

    DiscountCardDAO discountCardDAO = new DiscountCardDAO();
    private final List<DiscountCard> discountCardList = new ArrayList<>();
    private DiscountCard card1;
    private DiscountCard card2;

    @BeforeEach
    void init() {
        card1 = DiscountCard.newBuilder().setId(1).build();
        card2 = DiscountCard.newBuilder().setId(2).build();
        discountCardDAO.addDiscountCardToList(card1);
        discountCardDAO.addDiscountCardToList(card2);
        discountCardList.add(card1);
        discountCardList.add(card2);
    }

    @Test
    void findById() throws DaoException {
        assertThat(discountCardDAO.findById(1)).isEqualTo(card1);
    }

    @Test
    void addDiscountCardToList() {
        int initSize = discountCardDAO.getDiscountCardList().size();
        discountCardDAO.addDiscountCardToList(card1);
        int resultSize = discountCardDAO.getDiscountCardList().size();
        assertThat(resultSize).isEqualTo(initSize + 1);
    }

    @Test
    void getDiscountCardList() {
        assertThat(discountCardDAO.getDiscountCardList()).isEqualTo(discountCardList);
    }
}
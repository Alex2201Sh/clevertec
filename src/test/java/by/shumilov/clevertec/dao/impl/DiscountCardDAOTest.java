package by.shumilov.clevertec.dao.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.dao.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCardDAOTest {

    DiscountCardDAO discountCardDAO = new DiscountCardDAO();
    private List<DiscountCard> discountCardList = new ArrayList<>();
    private DiscountCard card1 = DiscountCard.newBuilder().setId(1).build();
    private DiscountCard card2 = DiscountCard.newBuilder().setId(2).build();

    @BeforeEach
    void init() {
        discountCardDAO.addDiscountCardToList(card1);
        discountCardDAO.addDiscountCardToList(card2);
        discountCardList.add(card1);
        discountCardList.add(card2);
    }

    @Test
    void findById() throws DaoException {
        assertEquals(card1, discountCardDAO.findById(1));
    }

    @Test
    void addDiscountCardToList() {
        int initSize = discountCardDAO.getDiscountCardList().size();
        discountCardDAO.addDiscountCardToList(card1);
        int resultSize = discountCardDAO.getDiscountCardList().size();
        assertEquals(initSize + 1, resultSize);
    }

    @Test
    void getDiscountCardList() {
        assertEquals(discountCardList, discountCardDAO.getDiscountCardList());
    }
}
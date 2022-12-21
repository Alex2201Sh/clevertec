package by.shumilov.clevertec.dao.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.dao.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class DiscountCardDAO is an implementation of
 * abstract class ItemDAODecorator.
 * Pattern "Decorator".
 */
public class DiscountCardDAO extends ItemDAODecorator {

    private final List<DiscountCard> discountCards;

    public DiscountCardDAO() {
        discountCards = new ArrayList<>();
    }

    @Override
    public DiscountCard findById(int id) throws DaoException {
        super.setItemList(discountCards);
        DiscountCard discountCard = (DiscountCard) super.findById(id);

        if (discountCard != null) {
            return discountCard;
        } else {
            throw new DaoException("Discount card № " + id + " doesn't exist.");
        }
    }

    public void addDiscountCardToList(DiscountCard discountCard) {
        discountCards.add(discountCard);
    }

    public List<DiscountCard> getDiscountCardList() {
        return discountCards;
    }
}

package by.shumilov.clevertec.dao;

import by.shumilov.clevertec.dao.impl.ItemDAODecorator;
import by.shumilov.clevertec.service.impl.InputDiscountCardsFromFile;
import by.shumilov.clevertec.service.impl.InputProductFromFile;

/**
 * Class DAOFactory implements "Factory" pattern
 * to create new DAO objects.
 */
public class DAOFactory {

    public ItemDAODecorator createAndFillDAOFromFile(
            final String dao, final String filename) {
        ItemDAODecorator itemDAODecorator = null;
        if (dao.contains("discount")) {
            itemDAODecorator = new InputDiscountCardsFromFile().inputFromFile(filename);
        } else if (dao.contains("product")) {
            itemDAODecorator = new InputProductFromFile().inputFromFile(filename);
        }
        return itemDAODecorator;
    }
}

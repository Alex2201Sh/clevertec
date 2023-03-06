package by.shumilov.clevertec.dao;

import by.shumilov.clevertec.dao.impl.DiscountCardDAO;
import by.shumilov.clevertec.dao.impl.ProductDAO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DAOFactoryTest {

    private final DAOFactory daoFactory = new DAOFactory();

    @Test
    void createAndFillProductDAOFromFileCheck() {
        assertThat(daoFactory
                .createAndFillDAOFromFile("product",
                        "src/test/resources/inputData/inputProductsValid.txt"))
                .isInstanceOf(ProductDAO.class);
    }

    @Test
    void createAndFillDiscountCardDAOFromFileCheck() {
        assertThat(daoFactory.createAndFillDAOFromFile("discount",
                "src/test/resources/inputData/inputDiscountCardsTestValid.txt"))
                .isInstanceOf(DiscountCardDAO.class);
    }

}
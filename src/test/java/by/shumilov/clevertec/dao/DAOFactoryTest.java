package by.shumilov.clevertec.dao;

import by.shumilov.clevertec.dao.impl.DiscountCardDAO;
import by.shumilov.clevertec.dao.impl.ProductDAO;
import by.shumilov.clevertec.service.impl.InputProductFromFile;
import by.shumilov.clevertec.view.impl.TextFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.assertSame;


class DAOFactoryTest {

    private final DAOFactory daoFactory = new DAOFactory();

    @Test
    void createAndFillDAOFromFile() {
        assertSame(ProductDAO.class,
                daoFactory.createAndFillDAOFromFile("product","input").getClass());
        assertSame(DiscountCardDAO.class,
                daoFactory.createAndFillDAOFromFile("discount","input").getClass());
    }

}
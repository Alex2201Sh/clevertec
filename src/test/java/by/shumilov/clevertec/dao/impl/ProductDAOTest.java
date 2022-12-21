package by.shumilov.clevertec.dao.impl;

import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.dao.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    ProductDAO productDAO = new ProductDAO();
    private final List<Product> productList = new ArrayList<>();
    private final Product product1 = Product.newBuilder().setId(1).build();
    private final Product product2 = Product.newBuilder().setId(2).build();

    @BeforeEach
    void init() {
        productDAO.addProductToList(product1);
        productDAO.addProductToList(product2);
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    void findById() throws DaoException {
        assertEquals(product1, productDAO.findById(1));
    }

    @Test
    void addProductToList() {
        int initSize = productDAO.getProductList().size();
        productDAO.addProductToList(product1);
        int resultSize = productDAO.getProductList().size();
        assertEquals(initSize + 1, resultSize);
    }

    @Test
    void getProductList() {
        assertEquals(productList, productDAO.getProductList());
    }

}
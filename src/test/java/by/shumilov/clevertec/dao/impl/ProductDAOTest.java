package by.shumilov.clevertec.dao.impl;

import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.dao.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static by.shumilov.clevertec.test_builder.TestBuilder.productBuilder;
import static org.assertj.core.api.Assertions.assertThat;


class ProductDAOTest {

    private final ProductDAO productDAO = new ProductDAO();
    private final List<Product> productList = new ArrayList<>();
    private final Product product1 = (Product) productBuilder().superId(1).build();
    private final Product product2 = (Product) productBuilder().superId(2).build();

    @BeforeEach
    void init() {
        productDAO.addProductToList(product1);
        productDAO.addProductToList(product2);
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    void findById() throws DaoException {
        assertThat(productDAO.findById(1)).isEqualTo(product1);
    }

    @Test
    void addProductToList() {
        int initSize = productDAO.getProductList().size();
        productDAO.addProductToList(product1);
        int resultSize = productDAO.getProductList().size();
        assertThat(resultSize).isEqualTo(initSize + 1);
    }

    @Test
    void getProductList() {
        assertThat(productDAO.getProductList()).isEqualTo(productList);
    }

}
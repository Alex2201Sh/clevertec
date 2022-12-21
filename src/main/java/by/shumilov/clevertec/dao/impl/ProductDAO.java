package by.shumilov.clevertec.dao.impl;

import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.dao.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ProductDAO is an implementation of
 * abstract class ItemDAODecorator.
 * Pattern "Decorator".
 */
public class ProductDAO extends ItemDAODecorator {

    private final List<Product> productList;

    public ProductDAO() {
        productList = new ArrayList<>();
    }

    @Override
    public Product findById(int id) throws DaoException {

        super.setItemList(productList);
        Product product = (Product) super.findById(id);

        if (product != null) {
            return product;
        } else {
            throw new DaoException("Product with id " + id + " doesn't exist.");
        }


    }

    public void addProductToList(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }
}

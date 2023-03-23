package by.shumilov.clevertec.dao.data_from_db;

import by.shumilov.clevertec.bean.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.core.util.Closer.close;

public class ProductDAOFromDB implements ItemDAO {

    private final Connection connection;

    private static final String SQL_SELECT_PRODUCT_BY_ID =
            "SELECT products.id,\n" +
                    "       products.name,\n" +
                    "       products.price,\n" +
                    "       products.is_promotion\n" +
                    "FROM products\n" +
                    "WHERE products.id = ?;";
    private static final String SQL_SELECT_ALL_PRODUCTS =
            "SELECT products.id,\n" +
                    "       products.name,\n" +
                    "       products.price,\n" +
                    "       products.is_promotion\n" +
                    "FROM products\n";
    private static final String SQL_SAVE_PRODUCT =
            "INSERT INTO products (id, name, price, is_promotion) VALUES (DEFAULT, ?, ?, ?)";
    private static final String SQL_UPDATE_PRODUCT =
            "UPDATE products \n" +
                    "SET name = ?, price = ?, is_promotion = ?" +
                    "WHERE id = ?;";
    private static final String SQL_DELETE_PRODUCT =
            "DELETE FROM products WHERE id = ?;";

    public ProductDAOFromDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Product findById(int id) {
        PreparedStatement statement = null;
        Product product = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                boolean isPromotion = resultSet.getBoolean("is_promotion");
                product = Product.builder()
                        .superId(id1)
                        .setName(name)
                        .setPrice(price)
                        .setPromotion(isPromotion)
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                close(statement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PRODUCTS);

            while (resultSet.next()) {
                Product product = Product.builder()
                        .superId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setPrice(resultSet.getDouble("price"))
                        .setPromotion(resultSet.getBoolean("is_promotion"))
                        .build();

                productList.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productList;
    }


    public void save(Product product) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_SAVE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setBoolean(3, product.getPromotion());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Product product) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setBoolean(3, product.getPromotion());
            statement.setInt(4, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_DELETE_PRODUCT);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

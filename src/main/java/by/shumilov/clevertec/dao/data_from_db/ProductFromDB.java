package by.shumilov.clevertec.dao.data_from_db;

import by.shumilov.clevertec.bean.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.logging.log4j.core.util.Closer.close;

public class ProductFromDB implements ItemDAO {

    private final Connection connection;

    public ProductFromDB(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_SELECT_PRODUCT_BY_ID =
            "SELECT products.id,\n" +
                    "       products.name,\n" +
                    "       products.price,\n" +
                    "       products.is_promotion\n" +
                    "from products\n" +
                    "where products.id = ?;";


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
                product = Product.newBuilder()
                        .setId(id1)
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
}

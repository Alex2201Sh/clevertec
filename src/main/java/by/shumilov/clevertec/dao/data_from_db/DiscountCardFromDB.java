package by.shumilov.clevertec.dao.data_from_db;

import by.shumilov.clevertec.bean.DiscountCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.logging.log4j.core.util.Closer.close;

public class DiscountCardFromDB implements ItemDAO {

    private final Connection connection;

    public DiscountCardFromDB(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_SELECT_PRODUCT_BY_ID =
            "SELECT public.discount_cards.id as id,\n" +
                    "       public.discount_cards.discount_percentage\n" +
                    "from public.discount_cards\n" +
                    "where public.discount_cards.id = ?;";


    @Override
    public DiscountCard findById(int id) {
        PreparedStatement statement = null;
        DiscountCard discountCard = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                int discountPercentage = resultSet.getInt("discount_percentage");
                discountCard = DiscountCard.newBuilder()
                        .setId(id1)
                        .setDiscountPercentage(discountPercentage)
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
        return discountCard;
    }
}

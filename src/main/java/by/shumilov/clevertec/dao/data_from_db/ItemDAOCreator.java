package by.shumilov.clevertec.dao.data_from_db;

import by.shumilov.clevertec.dao.data_from_db.db.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemDAOCreator {

    private static Connection connection;

    private static void init() {
        try {
            // Создать объект Connection подключенный к database.
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.init();
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductDAOFromDB getProductFromDBDao() {
        init();
        return new ProductDAOFromDB(connection);
    }

    public DiscountCardDAOFromDB getDiscountCardFromDB() {
        return new DiscountCardDAOFromDB(connection);
    }

}

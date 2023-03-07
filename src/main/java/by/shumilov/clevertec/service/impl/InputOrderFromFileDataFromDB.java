package by.shumilov.clevertec.service.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;
import by.shumilov.clevertec.dao.data_from_db.DiscountCardFromDB;
import by.shumilov.clevertec.dao.data_from_db.ProductFromDB;
import by.shumilov.clevertec.dao.data_from_db.db.ConnectionPool;
import by.shumilov.clevertec.service.InputOrder;
import by.shumilov.clevertec.service.ReceiptCreator;
import by.shumilov.clevertec.view.impl.TextFileReader;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The class is used to read the shopping list as a text file
 * and convert it to Receipt object.
 */
public class InputOrderFromFileDataFromDB implements InputOrder {

    private final TextFileReader textFileReader;
    public InputOrderFromFileDataFromDB(TextFileReader textFileReader) {
        this.textFileReader = textFileReader;
    }

    /**
     * The method creates database of Products and
     * Discount Cards from files in resources folder.
     *
     * @param args - name of files with products,
     *             discount cards and list of receipt lines.
     * @return Receipt object.
     */
    public Receipt inputOrder(final String[] args) {
        Connection connection = null;
        Receipt receipt;
        ReceiptCreator receiptCreator = new ReceiptCreator();
        try {
            // Создать объект Connection подключенный к database.
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.init();
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductFromDB productFromDB = new ProductFromDB(connection);
        DiscountCardFromDB discountCardsFromDB = new DiscountCardFromDB(connection);


        String productsInString = textFileReader.read(args[1]);
        String[] spliteStrings = productsInString.split(" ");
        for (String spliteString : spliteStrings) {
            String[] split = spliteString.split("-");
            if (!split[0].equalsIgnoreCase("card")) {
                Product productById = productFromDB
                        .findById(Integer.parseInt(split[0]));
                int quantity = Integer.parseInt(split[1]);
                receiptCreator.addLineToReceipt(ReceiptLine.anReceiptLine()
                        .setQuantity(quantity)
                        .setProduct(productById)
                        .build());
            } else {
                DiscountCard discountCardById =
                        discountCardsFromDB
                                .findById(Integer.parseInt(split[1]));
                receiptCreator
                        .getReceipt()
                        .setDiscountCard(discountCardById);
            }
        }

        receipt = receiptCreator.getReceipt();

        return receipt;
    }

}

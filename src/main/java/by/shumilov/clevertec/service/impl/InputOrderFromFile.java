package by.shumilov.clevertec.service.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;
import by.shumilov.clevertec.dao.DAOFactory;
import by.shumilov.clevertec.dao.exception.DaoException;
import by.shumilov.clevertec.dao.impl.DiscountCardDAO;
import by.shumilov.clevertec.dao.impl.ProductDAO;
import by.shumilov.clevertec.service.InputOrder;
import by.shumilov.clevertec.service.ReceiptCreator;
import by.shumilov.clevertec.view.impl.TextFileReader;

/**
 * The class is used to read the shopping list as a text file
 * and convert it to Receipt object.
 */
public class InputOrderFromFile implements InputOrder {

    private final TextFileReader textFileReader;

    public InputOrderFromFile(TextFileReader textFileReader) {
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
        Receipt receipt = null;
        ReceiptCreator receiptCreator = new ReceiptCreator();
        DAOFactory daoFactory = new DAOFactory();
        ProductDAO productDAO = (ProductDAO) daoFactory
                .createAndFillDAOFromFile("product dao",
                        args[0]);
        DiscountCardDAO discountCardDAO = (DiscountCardDAO) daoFactory
                .createAndFillDAOFromFile("discount card dao",
                        args[1]);
        try {
            String productsInString = textFileReader.read(args[2]);

            String[] spliteStrings = productsInString.split(" ");
            for (String spliteString : spliteStrings) {
                String[] split = spliteString.split("-");
                if (!split[0].equalsIgnoreCase("card")) {
                    Product productById = productDAO
                            .findById(Integer.parseInt(split[0]));
                    int quantity = Integer.parseInt(split[1]);
                    receiptCreator.addLineToReceipt(ReceiptLine.anReceiptLine()
                            .setQuantity(quantity)
                            .setProduct(productById)
                            .build());
                } else {
                    DiscountCard discountCardById =
                            discountCardDAO
                                    .findById(Integer.parseInt(split[1]));
                    receiptCreator
                            .getReceipt()
                            .setDiscountCard(discountCardById);
                }
            }

            receipt = receiptCreator.getReceipt();
        } catch (DaoException daoException) {
            daoException.printStackTrace();
        }

        return receipt;
    }

}

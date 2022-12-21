package by.shumilov.clevertec.service;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.bean.Receipt;
import by.shumilov.clevertec.bean.ReceiptLine;
import by.shumilov.clevertec.dao.DAOFactory;
import by.shumilov.clevertec.dao.impl.DiscountCardDAO;
import by.shumilov.clevertec.dao.exception.DaoException;
import by.shumilov.clevertec.dao.impl.ProductDAO;
import by.shumilov.clevertec.view.impl.TextFileReader;

import java.net.URISyntaxException;

/**
 * The class is used to read the shopping list as a text file
 * and convert it to Receipt object.
 */
public class InputOrderFromFile {

    private final TextFileReader textFileReader = new TextFileReader();

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
        String inputDataLocation = "inputData/";
        ProductDAO productDAO = (ProductDAO) daoFactory
                .createAndFillDAOFromFile("product dao",
                        inputDataLocation + args[0] + ".txt");
        DiscountCardDAO discountCardDAO = (DiscountCardDAO) daoFactory
                .createAndFillDAOFromFile("discount card dao",
                        inputDataLocation + args[1] + ".txt");
        try {
            String productsInString = textFileReader
                    .read(textFileReader
                            .getFileFromResource(
                                    inputDataLocation + args[2] + ".txt"));
            String[] spliteStrings = productsInString.split(" ");
            for (String spliteString : spliteStrings) {
                String[] split = spliteString.split("-");
                if (!split[0].equalsIgnoreCase("card")) {
                    Product productById = productDAO
                            .findById(Integer.parseInt(split[0]));
                    int quantity = Integer.parseInt(split[1]);
                    receiptCreator.addLineToReceipt(ReceiptLine.newBuilder()
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
        } catch (URISyntaxException | DaoException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }

        return receipt;
    }

}

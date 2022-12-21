package by.shumilov.clevertec.service.impl;

import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.dao.impl.ProductDAO;
import by.shumilov.clevertec.service.InputItems;
import by.shumilov.clevertec.view.impl.TextFileReader;

import java.net.URISyntaxException;

/**
 * Class InputProductFromFile uses to read array
 * of Products from file in "resources" folder.
 */
public class InputProductFromFile implements InputItems {

    private final TextFileReader textFileReader = new TextFileReader();

    /**
     * This method read file and return DAO with array.
     *
     * @param fileName - path to file in "resources" folder;
     * @return - ProductDAO object with array of Products.
     */
    @Override
    public ProductDAO inputFromFile(final String fileName) {
        ProductDAO productDAO = new ProductDAO();
        try {
            String productsInString = textFileReader
                    .read(textFileReader
                            .getFileFromResource(fileName));
            String[] splitedLines = productsInString.split("\n");
            for (String splitedLine : splitedLines) {
                String[] splitedProduct = splitedLine.split(" ");
                Product product = Product.newBuilder()
                        .setId(Integer.parseInt(splitedProduct[0]))
                        .setName(splitedProduct[1])
                        .setPrice(Double.parseDouble(splitedProduct[2]))
                        .setPromotion(Boolean.parseBoolean(splitedProduct[3]))
                        .build();
                productDAO.addProductToList(product);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return productDAO;
    }

}

package by.shumilov.clevertec.service.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.dao.impl.DiscountCardDAO;
import by.shumilov.clevertec.service.InputItems;
import by.shumilov.clevertec.view.impl.TextFileReader;

import java.net.URISyntaxException;

/**
 * Class InputDiscountCardsFromFile uses to read array
 * of DiscountCards from file in "resources" folder.
 */
public class InputDiscountCardsFromFile implements InputItems {

    private final TextFileReader textFileReader = new TextFileReader();

    /**
     * This method read file and return DAO with array.
     *
     * @param fileName - path to file in "resources" folder;
     * @return - DiscountCardDAO object with array of DiscountCards.
     */
    @Override
    public DiscountCardDAO inputFromFile(final String fileName) {
        DiscountCardDAO discountCardDAO = new DiscountCardDAO();
        try {
            String productsInString = textFileReader
                    .read(textFileReader
                            .getFileFromResource(fileName));
            String[] splitedLines = productsInString.split("\n");
            for (String splitedLine : splitedLines) {
                String[] splitedStrings = splitedLine.split(" ");
                DiscountCard discountCard = DiscountCard.newBuilder()
                        .setId(Integer.parseInt(splitedStrings[0]))
                        .setDiscountPercentage(Integer
                                .parseInt(splitedStrings[1]))
                        .build();
                discountCardDAO.addDiscountCardToList(discountCard);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return discountCardDAO;
    }

}

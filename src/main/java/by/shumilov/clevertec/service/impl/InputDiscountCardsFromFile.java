package by.shumilov.clevertec.service.impl;

import by.shumilov.clevertec.bean.DiscountCard;
import by.shumilov.clevertec.dao.impl.DiscountCardDAO;
import by.shumilov.clevertec.service.InputItems;
import by.shumilov.clevertec.view.impl.TextFileReader;

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
        String productsInString = textFileReader.read(fileName);
        String[] splitLines = productsInString.split("\n");
        for (String splitLine : splitLines) {
            String[] splitStrings = splitLine.split(" ");
            DiscountCard discountCard = DiscountCard.newBuilder()
                    .setId(Integer.parseInt(splitStrings[0]))
                    .setDiscountPercentage(Integer
                            .parseInt(splitStrings[1]))
                    .build();
            discountCardDAO.addDiscountCardToList(discountCard);
        }
        return discountCardDAO;
    }

}

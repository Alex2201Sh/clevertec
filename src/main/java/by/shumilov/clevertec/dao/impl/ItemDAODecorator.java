package by.shumilov.clevertec.dao.impl;

import by.shumilov.clevertec.bean.Item;
import by.shumilov.clevertec.dao.ItemDAO;
import by.shumilov.clevertec.dao.exception.DaoException;

import java.util.List;

/**
 * Class ItemDAODecorator uses like a BaseDecorator
 * in pattern "Decorator".
 */
public abstract class ItemDAODecorator implements ItemDAO {

    private List<? extends Item> itemList;

    /**
     * Method findById is a base common method for all
     * inherited classes;
     *
     * @param id - integer value of id;
     * @return - Item object.
     */
    public Item findById(int id) throws DaoException {
        Item item = null;
        int i = 0;
        while (i < itemList.size()) {
            if (itemList.get(i).getId() == id) {
                item = itemList.get(i);
                i = itemList.size();
            }
            i++;
        }
        return item;
    }

    public void setItemList(List<? extends Item> itemList) {
        this.itemList = itemList;
    }
}

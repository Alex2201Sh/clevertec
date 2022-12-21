package by.shumilov.clevertec.dao;

import by.shumilov.clevertec.bean.Item;
import by.shumilov.clevertec.dao.exception.DaoException;

/**
 * Base interface for all DAO's.
 */
public interface ItemDAO {
    Item findById(int id) throws DaoException;
}

package by.shumilov.clevertec.dao.data_from_db;

import by.shumilov.clevertec.bean.Item;
import by.shumilov.clevertec.dao.exception.DaoException;

public interface ItemDAO {
    Item findById(int id) throws DaoException;
}

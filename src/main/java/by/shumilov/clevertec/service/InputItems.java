package by.shumilov.clevertec.service;

import by.shumilov.clevertec.dao.ItemDAO;

/**
 * Base interface for input from files.
 */
public interface InputItems {
    ItemDAO inputFromFile(String filename);
}

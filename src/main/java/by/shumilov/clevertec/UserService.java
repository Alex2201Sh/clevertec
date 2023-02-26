package by.shumilov.clevertec;

import by.shumilov.clevertec.bean.User;

public interface UserService {
    User getUser(int id);
    void postUser(User user);
    void deleteUser(User user);
    void putUser(User user);

}

package by.shumilov.clevertec.dao_user;

import by.shumilov.clevertec.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOFactoryImpl implements UserDAOFactory {

    @Override
    public UserDAO getUserDao() {
        UserDAO userDAO = new UserDAO();
        initCollection().forEach(userDAO::addUser);
        return userDAO;
    }

    private List<User> initCollection() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = createUser(i, "name" + i, "123abc" + i, i + "test@test.by");
            userList.add(user);
        }
        return userList;
    }

    private User createUser(int id, String name, String password, String email) {
        User.Builder builder = User.newBuilder();
        if (id != 0) {
            builder.setId(id);
        }
        if (name != null) {
            builder.setName(name);
        }
        if (password != null) {
            builder.setPassword(password);
        }
        if (email != null) {
            builder.setEmail(email);
        }
        return builder.build();
    }
}

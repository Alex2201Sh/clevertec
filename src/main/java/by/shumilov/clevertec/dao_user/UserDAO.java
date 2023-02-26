package by.shumilov.clevertec.dao_user;

import by.shumilov.clevertec.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final List<User> userList = new ArrayList<>();

    //CREATE
    public void addUser(User user) {
        userList.add(user);
    }

    //READ
    public User getUserById(int id) {
        return userList.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    //UPDATE
    public void updateUser(User user) {
        User userFromDAO = getUserById(user.getId());
        int index = userList.indexOf(userFromDAO);
        userList.set(index, user);
    }

    //DELETE
    public void deleteUser(User user) {
        User userById = getUserById(user.getId());
        userList.remove(userById);
    }

    public void addCollection(List<User> list) {
        userList.addAll(list);
    }


}

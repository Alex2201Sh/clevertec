package by.shumilov.clevertec;

import by.shumilov.clevertec.bean.User;
import by.shumilov.clevertec.builder.UserDOMBuilder;

import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        UserDOMBuilder userDOMBuilder = new UserDOMBuilder();
        userDOMBuilder.buildSetUsers("src/main/resources/users.xml");
        Set<User> users = userDOMBuilder.getUsers();
        System.out.println(users);
    }
}

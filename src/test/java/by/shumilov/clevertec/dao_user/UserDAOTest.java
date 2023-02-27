package by.shumilov.clevertec.dao_user;

import by.shumilov.clevertec.bean.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final UserDAO userDAO = new UserDAO();

    @Test
    void addUser() throws NoSuchFieldException, IllegalAccessException {
        int testId = 999999999;
        User testUser = User.newBuilder().setId(testId).build();
        userDAO.addUser(testUser);
        Field userList = userDAO.getClass().getDeclaredField("userList");
        userList.setAccessible(true);
        List<User> userListFromDao = (List<User>) userList.get(userDAO);
        Assertions.assertThat(userListFromDao.contains(testUser)).isTrue();
    }

    @Test
    void getUserById() {
        int testId = 999999999;
        User testUser = User.newBuilder().setId(testId).build();
        userDAO.addUser(testUser);
        User userById = userDAO.getUserById(testId);
        Assertions.assertThat(userById).isEqualTo(testUser);
    }

    @Test
    void updateUser() {
        int testId = 999999999;
        String testName = "test name";
        User testUser = User.newBuilder().setId(testId).setName(testName).build();
        userDAO.addUser(testUser);
        String newTestName = "new test name";
        testUser.setName(newTestName);
        userDAO.updateUser(testUser);
        User userById = userDAO.getUserById(testId);
        Assertions.assertThat(userById.getName()).isEqualTo(newTestName);
    }

    @Test
    void deleteUser() {
        int testId = 999999999;
        String testName = "test name";
        User testUser = User.newBuilder().setId(testId).setName(testName).build();
        userDAO.addUser(testUser);
        userDAO.deleteUser(testUser);
        User userById = userDAO.getUserById(testId);
        Assertions.assertThat(userById).isNull();
    }

    @Test
    void addCollection() throws NoSuchFieldException, IllegalAccessException {
        List<User> userTestList = Stream
                .generate(() -> User.newBuilder().build())
                .limit(100)
                .collect(Collectors.toList());
        userDAO.addCollection(userTestList);
        Field userList = userDAO.getClass().getDeclaredField("userList");
        userList.setAccessible(true);
        List<User> userListFromDao = (List<User>) userList.get(userDAO);
        Assertions.assertThat(userListFromDao).isEqualTo(userTestList);
    }
}
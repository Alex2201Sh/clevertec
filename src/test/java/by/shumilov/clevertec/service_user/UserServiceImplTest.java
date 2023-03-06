package by.shumilov.clevertec.service_user;

import by.shumilov.clevertec.bean.User;
import by.shumilov.clevertec.cache.Cache;
import by.shumilov.clevertec.dao_user.UserDAO;
import by.shumilov.clevertec.dao_user.UserDAOFactoryImpl;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceImplTest {
    private final UserServiceImpl service = new UserServiceImpl(new UserDAOFactoryImpl());

    @Test
    void getUser() {
        int testId = 999999;
        User expectedUser = User.newBuilder().setId(testId).build();
        service.postUser(expectedUser);
        User actualUser = service.getUser(testId);
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void postUser() throws NoSuchFieldException, IllegalAccessException {
        Field userDaoField = service.getClass().getDeclaredField("userDao");
        userDaoField.setAccessible(true);
        UserDAO userDAO = (UserDAO) userDaoField.get(service);
        Field cacheField = service.getClass().getDeclaredField("cache");
        cacheField.setAccessible(true);
        Cache cache = (Cache) cacheField.get(service);
        int testId = 999999;
        User expectedUser = User.newBuilder().setId(testId).build();
        service.postUser(expectedUser);
        User userFromCache = (User) cache.get(testId);
        User userFromDao = userDAO.getUserById(testId);
        assertThat(userFromCache).isEqualTo(userFromDao).isEqualTo(expectedUser);
    }

    @Test
    void deleteUser() {
        int testId = 999999;
        User testUser = User.newBuilder().setId(testId).build();
        service.postUser(testUser);
        service.deleteUser(testUser);
        assertThat(service.getUser(testId)).isNull();
    }

    @Test
    void putUser() {
        int testId = 999999;
        User testUser = User.newBuilder().setId(testId).build();
        service.postUser(testUser);
        String expectedName = "new name";
        testUser.setName(expectedName);
        service.putUser(testUser);
        String actualNameFromService = service.getUser(testId).getName();
        assertThat(actualNameFromService).isEqualTo(expectedName);
    }
}
package by.shumilov.clevertec.service_user;

import by.shumilov.clevertec.bean.User;
import by.shumilov.clevertec.cache.Cache;
import by.shumilov.clevertec.cache.CacheCreator;
import by.shumilov.clevertec.dao_user.UserDAO;
import by.shumilov.clevertec.dao_user.UserDAOFactory;
import by.shumilov.clevertec.dao_user.UserDAOFactoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserDAOFactory factory = new UserDAOFactoryImpl();
    private UserDAO userDao = factory.getUserDao();
    private final UserServiceImpl service = new UserServiceImpl();


    @Test
    void getUser() {
        int testId = 999999;
        User expectedUser = User.newBuilder().setId(testId).build();
        service.postUser(expectedUser);
        User actualUser = service.getUser(testId);
        Assertions.assertThat(actualUser).isEqualTo(expectedUser);
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
        Assertions.assertThat(userFromCache).isEqualTo(userFromDao).isEqualTo(expectedUser);
    }

    @Test
    void deleteUser() {
        int testId = 999999;
        User testUser = User.newBuilder().setId(testId).build();
        service.postUser(testUser);
        service.deleteUser(testUser);
        Assertions.assertThat(service.getUser(testId)).isNull();
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
        Assertions.assertThat(actualNameFromService).isEqualTo(expectedName);
    }
}
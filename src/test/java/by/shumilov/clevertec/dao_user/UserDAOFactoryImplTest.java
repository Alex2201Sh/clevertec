package by.shumilov.clevertec.dao_user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOFactoryImplTest {

    @Test
    void getUserDao() {
        UserDAOFactoryImpl factory = new UserDAOFactoryImpl();
        UserDAO userDaoFromFactory = factory.getUserDao();
        Assertions.assertThat(userDaoFromFactory).isInstanceOf(UserDAO.class);
    }
}
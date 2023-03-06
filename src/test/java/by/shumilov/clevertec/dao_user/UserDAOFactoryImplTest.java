package by.shumilov.clevertec.dao_user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDAOFactoryImplTest {

    @Test
    void getUserDao() {
        UserDAOFactoryImpl factory = new UserDAOFactoryImpl();
        UserDAO userDaoFromFactory = factory.getUserDao();
        assertThat(userDaoFromFactory).isInstanceOf(UserDAO.class);
    }
}
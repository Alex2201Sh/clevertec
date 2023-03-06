package by.shumilov.clevertec.builder;

import by.shumilov.clevertec.bean.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDOMBuilderTest {

    AbstractUserBuilder abstractUserBuilder = new UserDOMBuilder();

    @Test
    void buildSetUsers() {
        abstractUserBuilder.buildSetUsers("src/test/resources/inputData/users.xml");
        Set<User> users = abstractUserBuilder.getUsers();
        Assertions.assertThat(users).isNotEmpty();
    }
}
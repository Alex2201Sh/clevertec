package by.shumilov.clevertec.builder;

import by.shumilov.clevertec.bean.User;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserDOMBuilderTest {

    private final AbstractUserBuilder abstractUserBuilder = new UserDOMBuilder();
    @Test
    void buildSetUsers() {
        abstractUserBuilder.buildSetUsers("src/test/resources/inputData/users.xml");
        Set<User> users = abstractUserBuilder.getUsers();
        assertThat(users).isNotEmpty();
    }
}
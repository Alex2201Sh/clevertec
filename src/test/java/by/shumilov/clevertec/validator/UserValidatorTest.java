package by.shumilov.clevertec.validator;

import by.shumilov.clevertec.bean.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserValidatorTest {

    private final UserValidator validator = new UserValidator();
    @ParameterizedTest
    @MethodSource("getArguments")
    void userValidate(User user, boolean expected) {
        boolean actual = validator.userValidate(user);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(User.newBuilder().setEmail("test@test.by").build(), false),
                Arguments.of(User.newBuilder().setPassword("password").build(), false),
                Arguments.of(User.newBuilder().setEmail("test@test.by")
                        .setPassword("password").build(), true)
        );
    }
}
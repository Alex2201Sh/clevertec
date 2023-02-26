package by.shumilov.clevertec.validator;

import by.shumilov.clevertec.bean.User;

import java.util.regex.Pattern;

public class UserValidator {

    public static void main(String[] args) {
        UserValidator userValidator = new UserValidator();
        User user = User.newBuilder().build();
        System.out.println(userValidator.userValidate(user));
    }

    public boolean userValidate(User user) {
        boolean passwordValidation = Pattern.compile("([A-Za-z0-9-]{8})")
                .matcher(user.getPassword()).matches();
        boolean emailValidation = Pattern
                .compile("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})")
                .matcher(user.getEmail()).matches();
        return passwordValidation && emailValidation;
    }

}

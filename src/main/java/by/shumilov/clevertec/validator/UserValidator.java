package by.shumilov.clevertec.validator;

import by.shumilov.clevertec.bean.User;

import java.util.regex.Pattern;

public class UserValidator {
    public boolean userValidate(User user) {
        boolean passwordValidation = Pattern
                .compile("([A-Za-z0-9-]{8})")
                .matcher(user.getPassword() != null ? user.getPassword() : "")
                .matches();
        boolean emailValidation = Pattern
                .compile("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})")
                .matcher(user.getEmail() != null ? user.getEmail() : "")
                .matches();
        return passwordValidation && emailValidation;
    }

}

package by.shumilov.clevertec.builder;

import by.shumilov.clevertec.bean.User;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractUserBuilder {
    protected Set<User> users;

    protected AbstractUserBuilder() {
        users = new HashSet<>();
    }

    protected AbstractUserBuilder(Set<User> users) {
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }

    public abstract void buildSetUsers(String fileName);

}


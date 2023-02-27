package by.shumilov.clevertec.proxy;

import by.shumilov.clevertec.bean.User;
import by.shumilov.clevertec.service_user.UserService;
import by.shumilov.clevertec.service_user.UserServiceImpl;

import java.lang.reflect.Proxy;

public class UserServiceProxy implements UserService {

    private static UserService userService;

    static {
        userService = new UserServiceImpl();
        ClassLoader loader = userService.getClass().getClassLoader();
        Class<?>[] interfaces = userService.getClass().getInterfaces();
        userService = (UserService) Proxy.newProxyInstance(loader, interfaces, new UserServiceInvocationHandler(userService));
    }

    @Override
    public User getUser(int id) {
        return userService.getUser(id);
    }

    @Override
    public void postUser(User user) {
        userService.postUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }

    @Override
    public void putUser(User user) {
        userService.putUser(user);
    }
}

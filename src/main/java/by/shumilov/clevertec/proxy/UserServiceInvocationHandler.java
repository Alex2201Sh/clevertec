package by.shumilov.clevertec.proxy;

import by.shumilov.clevertec.annotation.CustomAnnotation;
import by.shumilov.clevertec.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserServiceInvocationHandler implements InvocationHandler {

    private final UserService userService;

    public UserServiceInvocationHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getAnnotation(CustomAnnotation.class) != null ||
                userService.getClass().getMethod(method.getName(),
                                method.getParameterTypes())
                        .getAnnotation(CustomAnnotation.class) != null) {
            Object result = method.invoke(userService, args);
            System.out.println("Invocation happened via proxy.");
            return result;
        }
        return proxy;
    }
}

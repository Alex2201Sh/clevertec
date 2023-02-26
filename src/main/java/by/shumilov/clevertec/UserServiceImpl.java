package by.shumilov.clevertec;

import by.shumilov.clevertec.annotation.CustomAnnotation;
import by.shumilov.clevertec.bean.User;
import by.shumilov.clevertec.cache.Cache;
import by.shumilov.clevertec.cache.CacheCreator;
import by.shumilov.clevertec.dao_user.UserDAO;
import by.shumilov.clevertec.dao_user.UserDAOFactory;
import by.shumilov.clevertec.dao_user.UserDAOFactoryImpl;

public class UserServiceImpl implements UserService{

    private final UserDAOFactory factory = new UserDAOFactoryImpl();
    private final Cache<Integer,User> cache = CacheCreator.createCache("application.yml");
    private final UserDAO userDao = factory.getUserDao();

    @Override
    @CustomAnnotation
    public User getUser(int id) {
        User user;
        User userFromCache = cache.get(id);
        if (userFromCache != null) {
            user = userFromCache;
        } else {
            user = userDao.getUserById(id);
        }
        return user;
    }

    @Override
    public void postUser(User user){
        if (userDao.getUserById(user.getId())!=null){
            userDao.addUser(user);
            cache.put(user.getId(), user);
        } else {
            putUser(user);
        }
    }

    @Override
    public void deleteUser(User user){
        userDao.deleteUser(user);
        cache.put(user.getId(),null);
    }

    @Override
    public void putUser(User user){
        userDao.updateUser(user);
        cache.put(user.getId(),user);
    }


}

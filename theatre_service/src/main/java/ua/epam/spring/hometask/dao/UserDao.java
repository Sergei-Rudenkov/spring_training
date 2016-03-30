package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergei_rudenkov on 30.3.16.
 */
public class UserDao {

    public static Set<User> users = new HashSet();

    public Set<User> getAll() {
        return users;
    }

    public void remove(User user){
        users.remove(user);
    }

    public void put(User user){
        users.add(user);
    }
}

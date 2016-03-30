package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    UserDao userDao;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDao.getAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
    }

    @Override
    public User save(@Nonnull User user) {
        userDao.getAll().add(user);
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        userDao.getAll().remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userDao.getAll().stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }
}

package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.dao.DataClass;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
public class UserService implements IUserService {

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return DataClass.users.stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
    }

    @Override
    public User save(@Nonnull User user) {
        DataClass.users.add(user);
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        DataClass.users.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return DataClass.users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return DataClass.users;
    }
}

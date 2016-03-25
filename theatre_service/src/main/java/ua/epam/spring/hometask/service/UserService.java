package ua.epam.spring.hometask.service;

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
    List users = new ArrayList<User>();

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return null;
    }

    @Override
    public User save(@Nonnull User user) {
        System.out.println("Not implemented yet");
        System.out.println(user.getFirstName());
        return null;
    }

    @Override
    public void remove(@Nonnull User user) {

    }

    @Override
    public User getById(@Nonnull Long id) {
        return null;
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return null;
    }
}

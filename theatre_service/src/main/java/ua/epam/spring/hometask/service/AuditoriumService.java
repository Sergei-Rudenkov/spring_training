package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
@Service
public class AuditoriumService implements IAuditoriumService {

    @Autowired
    AuditoriumDao auditoriumDao;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumDao.getAll();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDao.getAll().stream().filter(auditorium -> auditorium.getName().equals(name)).findFirst().get();
    }
}

package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.dao.DataClass;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
public class AuditoriumService implements IAuditoriumService {

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return DataClass.audiences;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return null;
    }
}

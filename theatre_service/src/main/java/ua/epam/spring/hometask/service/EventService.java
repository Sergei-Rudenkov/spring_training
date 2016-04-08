package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.service_interfaces.IEventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
@Service
public class EventService implements IEventService {

    @Autowired
    EventDao eventDao;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDao.getAll().stream().filter(event -> event.getName().equals(name)).findFirst().get();
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return eventDao.getAll().stream().filter(event -> event.airsOnDates(from, to)).collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return eventDao.getAll().stream().filter(event -> event.airsOnDate(to.toLocalDate())).collect(Collectors.toSet());
    }

    @Override
    public Event save(@Nonnull Event event) {
        eventDao.getAll().add(event);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventDao.getAll().remove(event);

    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDao.getAll().stream().filter(event -> event.getId().equals(id)).findFirst().get();
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }
}

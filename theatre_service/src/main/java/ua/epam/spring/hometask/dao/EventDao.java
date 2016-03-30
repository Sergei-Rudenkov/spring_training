package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergei_rudenkov on 30.3.16.
 */
public class EventDao {
    public Set<Event> events = new HashSet<>();

    public Set<Event> getAll() {
        return events;
    }

    public void remove(Event auditorium){
        events.remove(auditorium);
    }

    public void put(Event auditorium){
        events.add(auditorium);
    }
}

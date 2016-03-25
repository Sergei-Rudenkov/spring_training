package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.util.Set;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
public class DataClass {
    public static Set<Auditorium> audiences;
    public static Set<Event> events;
    public static Set<User> users;

    public void setAudiences(Set<Auditorium> audiences) {
        DataClass.audiences = audiences;
    }

    public void setEvents(Set<Event> events) {
        DataClass.events = events;
    }

    public void setUsers(Set<User> users) {
        DataClass.users = users;
    }
}

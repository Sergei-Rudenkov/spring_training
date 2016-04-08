package ua.epam.spring.hometask.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by sergei_rudenkov on 30.3.16.
 */
@Component
public class TicketDao {

    Set<Ticket> tickets = new HashSet<>();

    public Set<Ticket> getAll() {
        return tickets;
    }

    public void remove(Ticket ticket){
        tickets.remove(ticket);
    }

    public void put(Ticket ticket){
        tickets.add(ticket);
    }
}

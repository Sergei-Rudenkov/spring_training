package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Ticket;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergei_rudenkov on 30.3.16.
 */
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

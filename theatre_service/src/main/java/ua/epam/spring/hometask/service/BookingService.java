package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.dao.DataClass;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
public class BookingService implements IBookingService {
    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        return 0;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        for(Ticket ticket : tickets){
            NavigableSet<Ticket> targetedUserTickets = ticket.getUser().getTickets();
            targetedUserTickets.add(ticket);
            ticket.getUser().setTickets(targetedUserTickets);
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Set<Ticket> allTickets = new HashSet<>();
        for(User user : DataClass.users){
            allTickets.addAll(user.getTickets());
        }
        return allTickets.stream().filter(ticket -> ticket.getDateTime().equals(dateTime) & ticket.getEvent().equals(event)).collect(Collectors.toSet());
    }
}

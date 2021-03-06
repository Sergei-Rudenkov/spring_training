package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.spring.hometask.dao.UserDao;
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
@Service
@Transactional(propagation= Propagation.SUPPORTS)
public class BookingService implements IBookingService {

    @Autowired
    UserDao userDao;

    @Autowired
    private IDiscountService discountService;

    @Override
    @Transactional
    public void buyTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        double basePrice = event.getBasePrice();
        double discount = discountService.getDiscount(user, event, dateTime, seats.size());
        double priceAllowance = 1.0;
        switch (event.getRating()) {
            case LOW:
                priceAllowance = 0.8;
                break;
            case HIGH:
                priceAllowance = 1.5;
        }
        user.getAccount().setPrepaidMoney(user.getAccount().getPrepaidMoney() - basePrice * priceAllowance * (1 - (discount / 100)) * seats.size());
        for (Long seat : seats)
            user.getTickets().add(new Ticket(user, event, dateTime, seat));
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
        for(User user : userDao.getAll()){
            allTickets.addAll(user.getTickets());
        }
        return allTickets.stream().filter(ticket -> ticket.getDateTime().equals(dateTime) & ticket.getEvent().equals(event)).collect(Collectors.toSet());
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}

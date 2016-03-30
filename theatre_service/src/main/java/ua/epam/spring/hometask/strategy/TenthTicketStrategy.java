package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;

/**
 * Created by sergei_rudenkov on 3/26/16.
 */
public class TenthTicketStrategy implements DiscountStrategy {
    @Override
    public Double checkDiscount(LocalDate date, User user, long numberOfTickets) {
        Double discount = 0.0;
        int ticketsBeforeDiscount = (int) ((user.getTickets().size() % 10.0) + numberOfTickets);
        if (ticketsBeforeDiscount >= 10.0) {
            discount = 10.0;
            return discount * (ticketsBeforeDiscount / 10.0);
        }
        return discount;
    }
}

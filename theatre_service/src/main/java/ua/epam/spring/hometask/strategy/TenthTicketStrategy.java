package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;

/**
 * Created by sergei_rudenkov on 3/26/16.
 */
public class TenthTicketStrategy implements DiscountStrategy {
    @Override
    public double checkDiscount(LocalDate date, User user, long numberOfTickets) {
        double discount = 0;
        int ticketsBeforeDiscount = (int) ((user.getTickets().size() % 10) + numberOfTickets);
        if (ticketsBeforeDiscount >= 10) {
            discount = 10;
            return discount * (ticketsBeforeDiscount / 10);
        }
        return discount;
    }
}

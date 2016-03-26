package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;

/**
 * Created by sergei_rudenkov on 3/26/16.
 */
public class TenthTicketStrategy implements DiscountStrategy {
    @Override
    public byte checkDiscount(LocalDate date, User user) {
        if (user.getTickets().size() % 10 == 0){
            return 10;
        }
        return 0;
    }
}

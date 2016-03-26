package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;

/**
 * Created by sergei_rudenkov on 3/26/16.
 */
public interface DiscountStrategy {
    byte checkDiscount(LocalDate date, User user);
}

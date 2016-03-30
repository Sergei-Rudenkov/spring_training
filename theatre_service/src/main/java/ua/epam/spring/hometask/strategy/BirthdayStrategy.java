package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by sergei_rudenkov on 3/26/16.
 */
public class BirthdayStrategy implements DiscountStrategy{
    @Override
    public Double checkDiscount(LocalDate eventDate, User user, long numberOfTickets){
        if(Math.abs(ChronoUnit.DAYS.between(user.getBirthday().minusYears(user.getBirthday().getYear()), eventDate.minusYears(eventDate.getYear()))) <= 5){
            return 5.0;
        }
        return 0.0;
    }
}

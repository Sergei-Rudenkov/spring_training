package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by sergei_rudenkov on 3/26/16.
 */
public class BirthdayStrategy implements DiscountStrategy{
    @Override
    public byte checkDiscount(LocalDate eventDate, User user){
        if(ChronoUnit.DAYS.between(eventDate, user.getBirthday()) <= 5){
            return 5;
        }
        return 0;
    }
}

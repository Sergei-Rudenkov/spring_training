package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.strategy.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
public class DiscountService implements IDiscountService {

    private List<DiscountStrategy> discountStrategies;

    public DiscountService(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public double getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        List<Double> discountPercentage = new ArrayList<>();
        for(DiscountStrategy strategy : discountStrategies){
            discountPercentage.add(strategy.checkDiscount(airDateTime.toLocalDate(), user, numberOfTickets));
        }
        return discountPercentage.stream().max(Double::compareTo).orElse(0.0) / numberOfTickets;
    }
}

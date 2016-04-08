package ua.epam.spring.hometask.service;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.service_interfaces.IDiscountService;
import ua.epam.spring.hometask.strategy.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei_rudenkov on 25.3.16.
 */
@Service
public class DiscountService implements IDiscountService {

    private List<DiscountStrategy> discountStrategies;

    @Override
    public double getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        List<Double> discountPercentage = new ArrayList<>();
        for(DiscountStrategy strategy : discountStrategies){
            discountPercentage.add(strategy.checkDiscount(airDateTime.toLocalDate(), user, numberOfTickets));
        }
        return discountPercentage.stream().max(Double::compareTo).orElse(0.0) / numberOfTickets;
    }

    public void setDiscountStrategies(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}

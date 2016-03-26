package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.strategy.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        List<Byte> discountPercentage = new ArrayList<>();
        for(DiscountStrategy strategy : discountStrategies){
            discountPercentage.add(strategy.checkDiscount(airDateTime.toLocalDate(), user));
        }
        return discountPercentage.stream().max(Byte::compareTo).orElse((byte) 0);
    }
}

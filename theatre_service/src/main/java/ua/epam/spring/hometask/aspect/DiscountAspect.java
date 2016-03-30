package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.strategy.BirthdayStrategy;
import ua.epam.spring.hometask.strategy.DiscountStrategy;
import ua.epam.spring.hometask.strategy.TenthTicketStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergei_rudenkov on 29.3.16.
 */
@Aspect
public class DiscountAspect {

    private Map<Class<? extends DiscountStrategy>, Integer> totalDiscountCounter = new HashMap<>();
    private Map<Class<? extends DiscountStrategy>, Map<User, Integer>> userDiscountCounter = new HashMap<>();

    {
        totalDiscountCounter.put(BirthdayStrategy.class, 0);
        totalDiscountCounter.put(TenthTicketStrategy.class, 0);
        userDiscountCounter.put(TenthTicketStrategy.class, new HashMap<>());
        userDiscountCounter.put(BirthdayStrategy.class, new HashMap<>());
    }




    @AfterReturning(
            pointcut = "execution(java.lang.Double *.checkDiscount(..))",
            returning = "result")
    public void countDiscounts(JoinPoint joinPoint, Object result) {
        Object[] arguments = joinPoint.getArgs();
        User user = (User) arguments[1];
        Class clazz = joinPoint.getTarget().getClass();
        boolean discountGiven = Double.parseDouble(String.valueOf(result)) > 0.0;
        if(discountGiven) {
            totalDiscountCounter.put(clazz, totalDiscountCounter.get(clazz) + 1);
            Map<User, Integer> userDiscountOccurrences = userDiscountCounter.get(clazz);
            if(userDiscountOccurrences.containsKey(user)) {
                userDiscountOccurrences.put(user, userDiscountOccurrences.get(user) + 1);
            }else{
                userDiscountOccurrences.put(user, 1);
            }
            userDiscountCounter.put(clazz, userDiscountOccurrences);
            System.out.println(String.format("[ASPECT LOG]: %s, discount were given %d times", clazz.getSimpleName(),
                    totalDiscountCounter.get(clazz)));
            System.out.println(String.format("[ASPECT LOG]: %s, discount, for %s %s user were given %d times",
                    clazz.getSimpleName(), user.getFirstName(), user.getLastName(), userDiscountCounter.get(clazz).get(user)));
        }
    }
}

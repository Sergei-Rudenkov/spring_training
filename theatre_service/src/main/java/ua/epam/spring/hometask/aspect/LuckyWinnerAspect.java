package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Random;

/**
 * every time the bookTicket method is executed perform the checkLucky method for the user <br/>
 * that based on some randomness will return true or false. <br/>
 * If user is lucky, the ticketPrice changes to zero and ticket is booked, thus user pays nothing. <br/>
 * @author Sergei_Rudenkov
 */

@Aspect
public class LuckyWinnerAspect {

    @Around("execution(* ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    public double countDiscounts(ProceedingJoinPoint joinPoint) throws Throwable {
        if (isLucky()){
            return 0.0;
        }
        return (double) joinPoint.proceed(joinPoint.getArgs());
    }

    private boolean isLucky(){
        Random rand = new Random();
        return 1 == rand.nextInt(10000);
    }
}

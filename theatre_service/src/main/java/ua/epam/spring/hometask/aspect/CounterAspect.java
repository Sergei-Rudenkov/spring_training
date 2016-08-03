package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.*;

/**
 * Count how many times each event was accessed by name, how many times its prices were queried, <br/>
 * how many times its tickets were booked. Store counters in map. <br/>
 * @author Sergei_Rudenkov
 */
@Aspect
public class CounterAspect {
    private Map<String, Integer> callCounter = new HashMap<>();
    private Map<String, Integer> priceCounter = new HashMap<>();
    private Map<String, Integer> bookingCounter = new HashMap<>();

    @Pointcut("execution(* ua.epam.spring.hometask.service.EventService.getByName(..))")
    private void callEventByName() {
    }

    @AfterReturning("callEventByName()")
    public void countCallByName(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        String eventName = (String) arguments[0];
        if (callCounter.containsKey(eventName)) {
            callCounter.put(eventName, callCounter.get(eventName) + 1);
        } else {
            callCounter.put(eventName, 1);
        }
        System.out.println(String.format("[ASPECT LOG]: %s, was called by name %dth time", eventName, callCounter.get(eventName)));
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.BookingService.buyTicketsForEvent(..))")
    private void callEventTicketPrice() {
    }

    @AfterReturning("callEventTicketPrice()")
    public void countPriceCall(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        Event event = (Event) arguments[0];
        String eventName = event.getName();
        if (priceCounter.containsKey(eventName)) {
            priceCounter.put(eventName, priceCounter.get(eventName) + 1);
        } else {
            priceCounter.put(eventName, 1);
        }
        System.out.println(String.format("[ASPECT LOG]: %s, price was required %dth time", eventName, priceCounter.get(eventName)));
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.BookingService.bookTickets(..))")
    private void callBookTickets() {
    }

    @AfterReturning("callBookTickets()")
    public void countBookCall(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        Set<Ticket> tickets = (HashSet<Ticket>) arguments[0];
        Iterator<Ticket> ticketsIterator = tickets.iterator();
        String eventName = null;
        while (ticketsIterator.hasNext()) {
            eventName = ticketsIterator.next().getEvent().getName();
            if (bookingCounter.containsKey(eventName)) {
                bookingCounter.put(eventName, bookingCounter.get(eventName) + 1);
            } else {
                bookingCounter.put(eventName, 1);
            }
        }
        System.out.println(String.format("[ASPECT LOG]: %s, tickets were booked %dth times", eventName, bookingCounter.get(eventName)));
    }
}

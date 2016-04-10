package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.spring.hometask.dao.BookingCounterDao;
import ua.epam.spring.hometask.dao.CallCounterDao;
import ua.epam.spring.hometask.dao.PriceCounterDao;
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

    @Autowired
    private BookingCounterDao bookingCounterDao;

    @Autowired
    private CallCounterDao callCounterDao;

    @Autowired
    private PriceCounterDao priceCounterDao;

    @Pointcut("execution(* ua.epam.spring.hometask.service.EventService.getByName(..))")
    private void callEventByName() {
    }

    @AfterReturning("callEventByName()")
    public void countCallByName(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        String eventName = (String) arguments[0];
        if (callCounterDao.getAll().containsKey(eventName)) {
            callCounterDao.put(eventName, callCounterDao.get(eventName) + 1);
        } else {
            callCounterDao.put(eventName, 1);
        }
        System.out.println(String.format("[ASPECT LOG]: %s, was called by name %dth time", eventName, callCounterDao.get(eventName)));
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    private void callEventTicketPrice() {
    }

    @AfterReturning("callEventTicketPrice()")
    public void countPriceCall(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        Event event = (Event) arguments[0];
        String eventName = event.getName();
        if (priceCounterDao.getAll().containsKey(eventName)) {
            priceCounterDao.put(eventName, priceCounterDao.get(eventName) + 1);
        } else {
            priceCounterDao.put(eventName, 1);
        }
        System.out.println(String.format("[ASPECT LOG]: %s, price was required %dth time", eventName, priceCounterDao.get(eventName)));
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
            if (bookingCounterDao.getAll().containsKey(eventName)) {
                bookingCounterDao.put(eventName, bookingCounterDao.get(eventName) + 1);
            } else {
                bookingCounterDao.put(eventName, 1);
            }
        }
        System.out.println(String.format("[ASPECT LOG]: %s, tickets were booked %dth times", eventName, bookingCounterDao.get(eventName)));
    }
}

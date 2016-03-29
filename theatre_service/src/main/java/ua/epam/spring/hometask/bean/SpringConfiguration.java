package ua.epam.spring.hometask.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ua.epam.spring.hometask.aspect.CounterAspect;
import ua.epam.spring.hometask.dao.DataClass;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.*;
import ua.epam.spring.hometask.strategy.BirthdayStrategy;
import ua.epam.spring.hometask.strategy.DiscountStrategy;
import ua.epam.spring.hometask.strategy.TenthTicketStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by sergei_rudenkov on 29.3.16.
 */
@Configuration
@EnableAspectJAutoProxy
public class SpringConfiguration {

    @Bean(name = "first_event_dateTime")
    public LocalDateTime getEventDateTime() {
        return LocalDateTime.of(2020, 6, 15, 19, 30);
    }

    @Bean(name = "auditoriumService")
    public IAuditoriumService getAuditoriumService() {
        return new AuditoriumService();
    }

    @Bean(name = "discountStrategies")
    public List<DiscountStrategy> getDiscountStrategies(){
        return new ArrayList<DiscountStrategy>(){{
            add(new BirthdayStrategy());
            add(new TenthTicketStrategy());
        }};
    }

    @Bean(name = "discountService")
    public IDiscountService getDiscountService() {
        IDiscountService discountService = new DiscountService();
        discountService.setDiscountStrategies(getDiscountStrategies());
        return discountService;
    }

    @Bean(name = "bookingService")
    public IBookingService getBookingService() {
        return new BookingService();
    }

    @Bean(name = "eventService")
    public IEventService getEventService(){
        return new EventService();
    }

    @Bean(name = "userService")
    public IUserService getUserService(){
        return new UserService();
    }

    @Bean(name = "user1")
    public User getFirstUser(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("Foo");
        user.setLastName("Bar");
        user.setEmail("my@email.com");
        user.setBirthday(LocalDate.of(1992, 9, 25));
        return user;
    }

    @Bean(name = "user2")
    public User getSecondUser(){
        User user = new User();
        user.setId(2L);
        user.setFirstName("A");
        user.setLastName("Somebody");
        user.setEmail("somebody@a.b");
        user.setBirthday(LocalDate.of(1956, 4, 3));
        return user;
    }

    @Bean(name = "auditorium1")
    public Auditorium getFirstAuditorium(){
        Auditorium auditorium = new Auditorium();
        auditorium.setName("Big hall");
        auditorium.setNumberOfSeats(450);
        auditorium.setVipSeats(LongStream.rangeClosed(1, 15).boxed().collect(Collectors.toSet()));
        return auditorium;
    }

    @Bean(name = "auditorium2")
    public Auditorium getSecondAuditorium(){
        Auditorium auditorium = new Auditorium();
        auditorium.setName("Exhibition hall");
        auditorium.setNumberOfSeats(50);
        return auditorium;
    }

    @Bean(name = "auditorium3")
    public Auditorium getThirdAuditorium(){
        Auditorium auditorium = new Auditorium();
        auditorium.setName("Small hall");
        auditorium.setNumberOfSeats(150);
        return auditorium;
    }

    @Bean(name = "event1")
    public Event getEvent(){
        Event event = new Event();
        Arrays.asList(1,2,3).stream().max(Integer::compareTo).get();
        event.setId(1L);
        event.setName("Grand concert");
        event.setRating(EventRating.MID);
        event.setBasePrice(10);
        event.addAirDateTime(getEventDateTime());
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(getEventDateTime(), getFirstAuditorium());
        event.setAuditoriums(auditoriums);
        return event;
    }

    @Bean(name = "dataClass")
    public DataClass getData(){
        DataClass data = new DataClass();
        data.setAudiences(new HashSet<Auditorium>(){{
           add(getFirstAuditorium());
           add(getSecondAuditorium());
           add(getThirdAuditorium());
        }});
        data.setEvents(new HashSet<Event>(){{
            add(getEvent());
        }});
        data.setUsers(new HashSet<User>(){{
            add(getFirstUser());
            add(getSecondUser());
        }});
        return data;
    }

    @Bean(name = "ticket1")
    public Ticket getFirstTicket(){
        return new Ticket(getFirstUser(), getEvent(), getEventDateTime(), 1L);
    }

    @Bean(name = "ticket2")
    public Ticket getSecondTicket(){
        return new Ticket(getSecondUser(), getEvent(), getEventDateTime(), 2L);
    }

    @Bean(name = "aspect")
    public CounterAspect getCounterAspect(){
        return new CounterAspect();
    }
}

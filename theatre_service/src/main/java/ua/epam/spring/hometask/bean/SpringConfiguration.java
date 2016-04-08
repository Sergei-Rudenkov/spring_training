package ua.epam.spring.hometask.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ua.epam.spring.hometask.aspect.CounterAspect;
import ua.epam.spring.hometask.aspect.DiscountAspect;
import ua.epam.spring.hometask.aspect.LuckyWinnerAspect;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.*;
import ua.epam.spring.hometask.service.service_interfaces.*;
import ua.epam.spring.hometask.strategy.BirthdayStrategy;
import ua.epam.spring.hometask.strategy.DiscountStrategy;
import ua.epam.spring.hometask.strategy.TenthTicketStrategy;

import javax.sql.DataSource;
import java.io.IOException;
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
@ComponentScan("ua.epam.spring.hometask")
public class SpringConfiguration {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?autoReconnect=true");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean(name = "first_event_dateTime")
    public LocalDateTime getEventDateTime() {
        return LocalDateTime.of(2020, 6, 15, 19, 30);
    }

    @Bean(name = "birthdayStrategy")
    public DiscountStrategy getBirthdayStrategy(){
        return new BirthdayStrategy();
    }

    @Bean(name = "tenthTicketStrategy")
    public DiscountStrategy getTenthTicketStrategy(){
        return new TenthTicketStrategy();
    }

    @Bean(name = "discountStrategies")
    public List<DiscountStrategy> getDiscountStrategies(){
        return new ArrayList<DiscountStrategy>(){{
            add(getBirthdayStrategy());
            add(getTenthTicketStrategy());
        }};
    }

    @Bean(name = "discountService")
    public IDiscountService getDiscountService() {
        IDiscountService discountService = new DiscountService();
        discountService.setDiscountStrategies(getDiscountStrategies());
        return discountService;
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

    @Bean(name = "ticketDao")
    public TicketDao getTicketDao(){
        TicketDao ticketDao = new TicketDao();
        ticketDao.put(getFirstTicket());
        ticketDao.put(getSecondTicket());
        return ticketDao;
    }

    @Bean(name = "eventDao")
    public EventDao getEventDao(){
        EventDao eventDao = new EventDao();
        eventDao.put(getEvent());
        return eventDao;
    }

    @Bean(name = "ticket1")
    public Ticket getFirstTicket(){
        return new Ticket(getFirstUser(), getEvent(), getEventDateTime(), 1L);
    }

    @Bean(name = "ticket2")
    public Ticket getSecondTicket(){
        return new Ticket(getSecondUser(), getEvent(), getEventDateTime(), 2L);
    }

    @Bean(name = "counterAspect")
    public CounterAspect getCounterAspect(){
        return new CounterAspect();
    }

    @Bean(name = "discountAspect")
    public DiscountAspect getDiscountAspect(){
        return new DiscountAspect();
    }

    @Bean(name = "luckyAspect")
    public LuckyWinnerAspect getLuckyWinnerAspect(){
        return new LuckyWinnerAspect();
    }

    @Bean(name = "eventService")
    public IEventService getEventService(){
        return new EventService();
    }

    @Bean(name = "userService")
    public IUserService getUserService(){
        return new UserService();
    }
}

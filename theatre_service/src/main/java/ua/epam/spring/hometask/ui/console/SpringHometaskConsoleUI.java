package ua.epam.spring.hometask.ui.console;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.context.ApplicationContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.*;
import ua.epam.spring.hometask.ui.console.state.MainState;

/**
 * Simple console UI application for the hometask code. UI provides different
 * action to input and output data. In order for the application to work, the
 * Spring context initialization code should be placed into
 * {@link #initContext()} method.
 * 
 * @author Yuriy_Tkach
 */
public class SpringHometaskConsoleUI {

    private ApplicationContext context;

    public static void main(String[] args) {
        SpringHometaskConsoleUI ui = new SpringHometaskConsoleUI();
        ui.initContext();
        ui.run();
    }

    private void initContext() {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
    }

    private void run() {
        System.out.println("Welcome to movie theater console service");
        
        fillInitialData();

        MainState state = new MainState(context);

        state.run();

        System.out.println("Exiting.. Thank you.");
    }

    private void fillInitialData() {
        IUserService userService = context.getBean(UserService.class);
        IEventService eventService = context.getBean(EventService.class);
        IAuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        IBookingService bookingService = context.getBean(BookingService.class);
        
//        Auditorium auditorium = auditoriumService.getAll().iterator().next();
//        if (auditorium == null) {
//            throw new IllegalStateException("Failed to fill initial data - no auditoriums returned from AuditoriumService");
//        }
//        if (auditorium.getNumberOfSeats() <= 0) {
//            throw new IllegalStateException("Failed to fill initial data - no seats in the auditorium " + auditorium.getName());
//        }



        Ticket ticket1 = (Ticket) context.getBean("ticket_1");
        bookingService.bookTickets(Collections.singleton(ticket1));
        userService.save(ticket1.getUser());
        eventService.save(ticket1.getEvent());

        Ticket ticket2 = (Ticket) context.getBean("ticket_2");
        bookingService.bookTickets(Collections.singleton(ticket2));
        userService.save(ticket2.getUser());
        eventService.save(ticket2.getEvent());
    }
}

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

        MainState state = new MainState(context);

        state.run();

        System.out.println("Exiting.. Thank you.");
    }
}

package ua.epam.spring.hometask.ui.console;

import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.bean.SpringConfiguration;
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
        //context = new ClassPathXmlApplicationContext("spring_config.xml");
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }

    private void run() {
        System.out.println("Welcome to movie theater console service");

        MainState state = new MainState(context);

        state.run();

        System.out.println("Exiting.. Thank you.");
    }
}

package wo_spring_realization;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wo_spring_realization.loggers.ConsoleEventLogger;
import wo_spring_realization.loggers.IEventLogger;

/**
 * Created by Sergei_Rudenkov on 10/28/2015.
 */
public class App {

    private IEventLogger eventLogger;
    private Client client;
    private Event event;
    static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    public App(IEventLogger eventLogger, Client client) {
        this.eventLogger = eventLogger;
        this.client = client;
    }

    public static void main(String[] args) {
        App app = (App) ctx.getBean("app");
        app.logEvent("Some event for user 1");
        ctx.close();
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event = (Event) ctx.getBean("event");
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}

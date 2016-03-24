package wo_spring_realization;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wo_spring_realization.event.Event;
import wo_spring_realization.event.EventType;
import wo_spring_realization.loggers.IEventLogger;

import java.util.Map;

/**
 * Created by Sergei_Rudenkov on 10/28/2015.
 */
public class App {

    private IEventLogger defaultEventLogger;
    private Client client;
    private Event event;
    private Map<EventType, IEventLogger> loggers;
    private static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    public App(IEventLogger eventLogger, Client client, Map<EventType, IEventLogger> loggers) {
        this.defaultEventLogger = eventLogger;
        this.client = client;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.ERROR, "Some event for user 1");
        app.logEvent(EventType.INFO, "Some event 2 for user 1");
        app.logEvent(null, "Some event 52 for user 1");
        app.logEvent(EventType.INFO, "Some event 49 for user 1");
        ctx.close();
    }

    private void logEvent(EventType type, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event = (Event) ctx.getBean("event");
        event.setMsg(message);
        IEventLogger logger = loggers.get(type);
        if (logger == null){
            logger = this.defaultEventLogger;
        }
        logger.logEvent(event);
    }
}

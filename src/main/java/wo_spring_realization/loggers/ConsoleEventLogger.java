package wo_spring_realization.loggers;

import wo_spring_realization.Event;

/**
 * Created by Sergei_Rudenkov on 10/28/2015.
 */
public class ConsoleEventLogger implements IEventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }
}

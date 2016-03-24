package wo_spring_realization.loggers;

import wo_spring_realization.event.Event;


/**
 * Created by Sergei_Rudenkov on 10/28/2015.
 */
public interface IEventLogger {
    void logEvent(Event event);
}

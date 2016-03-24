package wo_spring_realization.loggers;

import wo_spring_realization.event.Event;

import java.util.Collection;

/**
 * Created by root on 30.10.15.
 */
public class CombinedEventLogger implements IEventLogger{

	Collection<IEventLogger> loggers;

	public CombinedEventLogger(Collection<IEventLogger> loggers) {
		this.loggers = loggers;
	}

	public void logEvent(Event event) {
		for (IEventLogger logger : loggers){
			logger.logEvent(event);
		}
	}
}

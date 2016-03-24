package wo_spring_realization.loggers;

import wo_spring_realization.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergei_Rudenkov on 10/29/2015.
 */
public class CacheFileEventLogger extends FileEventLogger implements IEventLogger {

    private List<Event> cache = new ArrayList<Event>();
    private int cacheSize;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event){
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }

    private void destroy(){
        if(!cache.isEmpty())
            writeEventsFromCache();
    }
}

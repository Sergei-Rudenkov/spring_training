package wo_spring_realization;

/**
 * Created by Sergei_Rudenkov on 10/28/2015.
 */
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }
}

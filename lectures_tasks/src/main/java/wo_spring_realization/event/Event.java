package wo_spring_realization.event;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Sergei_Rudenkov on 10/28/2015.
 */
public class Event {
    private long id;
    private DateFormat df;
    private String msg;
    private Date date;
    private Random rnd = new Random();

    public Event(Date date, DateFormat df) {
        this.df = df;
        this.date = date;
        this.id = Math.abs(rnd.nextLong());
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}

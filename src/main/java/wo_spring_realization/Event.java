package wo_spring_realization;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Sergei_Rudenkov on 10/28/2015.
 */
public class Event {
    private int id;
    private DateFormat df;
    private String msg;
    private Date date;

    public Event(Date date, DateFormat df) {
        this.df = df;
        this.date = date;
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

package ua.epam.spring.hometask.ui.console.state;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ua.epam.spring.hometask.dao.DataClass;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.IAuditoriumService;
import ua.epam.spring.hometask.service.IEventService;

/**
 * State for managing events
 *
 * @author Yuriy_Tkach
 */
public class EventManageState extends AbstractDomainObjectManageState<Event, IEventService> {

    @Autowired
    private IAuditoriumService auditoriumService;

    public EventManageState(ApplicationContext context) {
        super(context.getBean(IEventService.class));
        this.auditoriumService = context.getBean(IAuditoriumService.class);
    }

    @Override
    protected String getObjectName() {
        return Event.class.getSimpleName().toLowerCase(Locale.ROOT);
    }

    @Override
    protected void printObject(Event event) {
        System.out.println("[" + event.getId() + "] " + event.getName() + " (Rating: " + event.getRating() + ", Price: "
                + event.getBasePrice() + ")");
    }

    @Override
    protected Event createObject() {
        System.out.println("Adding event");
        long eventId = readIntInput("Id: ");
        String name = readStringInput("Name: ");
        EventRating rating = readEventRating();
        double basePrice = readDoubleInput("Base price: ");

        Event event = new Event();
        event.setId(eventId);
        event.setName(name);
        event.setRating(rating);
        event.setBasePrice(basePrice);
        return event;
    }

    @Override
    protected int printSubActions(int maxDefaultActions) {
        int index = maxDefaultActions;
        System.out.println(" " + (++index) + ") Find event by name");
        System.out.println(" " + (++index) + ") See event info (air dates, auditoriums)");
        System.out.println(" " + (++index) + ") Manage event info (assignee air dates, auditoriums)");
        return index - maxDefaultActions;
    }

    @Override
    protected void runSubAction(int action, int maxDefaultActions) {
        int index = action - maxDefaultActions;
        switch (index) {
            case 1:
                findEventByName();
                break;
            case 2:
                seeEventInfo();
                break;
            case 3:
                manageEventInfo();
                break;
            default:
                System.err.println("Unknown action");
        }
    }

    private void manageEventInfo() {
        System.out.println("Events:\n" + service.getAll().stream().map(e -> e.getId() + ") " + e.getName() + "\n").collect(Collectors.toList()));
        int id = readIntInput("Input event id: ");
        Event event = service.getById(Long.valueOf(id));
        Auditorium auditorium = readAuditorium();
        int quantity_of_air_dates = readIntInput("Input how many air dates you want to add: ");
        for (int i = 0; i < quantity_of_air_dates; i++) {
            LocalDateTime date = readDateTimeInput("Input air date (yyyy-MM-dd HH:mm): ");
            event.assignAuditorium(date, auditorium);
            event.addAirDateTime(date);
        }
    }

    private void seeEventInfo() {
        System.out.println("Events:\n" + service.getAll().stream().map(e -> e.getId() + ") " + e.getName() + "\n").collect(Collectors.toList()));
        int id = readIntInput("Input event id: ");
        Event event = service.getById(Long.valueOf(id));
        if (event == null) {
            System.out.println("Not found (searched for " + id + ")");
        } else {
            printDelimiter();

            AbstractState manageState = new EventInfoManageState(event, service, auditoriumService);
            manageState.run();
        }
    }

    private void findEventByName() {
        String name = readStringInput("Input event name: ");
        Event event = service.getByName(name);
        if (event == null) {
            System.out.println("Not found (searched for " + name + ")");
        } else {
            printObject(event);
        }
    }

    private EventRating readEventRating() {
        EventRating rating = null;
        do {
            String str = readStringInput("Rating (LOW, MID, HIGH): ");
            try {
                rating = EventRating.valueOf(str);
            } catch (Exception e) {
                rating = null;
            }
        } while (rating == null);
        return rating;
    }

    private Auditorium readAuditorium() {
        Auditorium auditorium = null;
        do {
            String str = readStringInput("Input audience name " + auditoriumService.getAll().stream().map(a -> a.getName()).collect(Collectors.toList())+ ": ");
            try {
                auditorium = auditoriumService.getByName(str);
            } catch (Exception e) {
                str = null;
            }
        } while (auditorium == null);
        return auditorium;
    }

}

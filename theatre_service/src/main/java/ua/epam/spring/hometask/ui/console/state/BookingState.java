package ua.epam.spring.hometask.ui.console.state;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.DomainObject;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.IAbstractDomainObjectService;
import ua.epam.spring.hometask.service.IBookingService;
import ua.epam.spring.hometask.service.IEventService;
import ua.epam.spring.hometask.service.IUserService;

public class BookingState extends AbstractState {

    @Autowired
    private final IBookingService bookingService;
    @Autowired
    private final IUserService userService;
    @Autowired
    private final IEventService eventService;

    public BookingState(ApplicationContext context) {
        this.bookingService = context.getBean(IBookingService.class);
        this.userService = context.getBean(IUserService.class);
        this.eventService = context.getBean(IEventService.class);
    }

    @Override
    protected void printDefaultInformation() {
        System.out.println("Lets book tickets!");
    }

    @Override
    protected int printMainActions() {
        System.out.println(" 1) Get tickets price");
        System.out.println(" 2) Book tickets");
        System.out.println(" 3) Get booked tickets");
        return 4;
    }

    @Override
    protected void runAction(int action) {
        switch (action) {
        case 1:
            getTicketsPrice();
            break;
        case 2:
            bookTickets();
            break;
        case 3:
            getBookedTickets();
            break;
        default:
            System.err.println("Unknown action");
        }
    }

    private void getBookedTickets() {
        System.out.println("> Select event: ");
        Event event = selectDomainObject(eventService, e -> e.getName());
        if (event == null) {
            System.err.println("No event found");
            return;
        }
        
        System.out.println("> Select air dates: ");
        LocalDateTime airDate = selectAirDate(event.getAirDates());
        
        printDelimiter();
        Set<Ticket> bookedTickets = bookingService.getPurchasedTicketsForEvent(event, airDate);
        bookedTickets.forEach(t -> System.out.println("Seat " + t.getSeat() + "\t for " + t.getUser().getEmail()));
    }

    private void bookTickets() {
        System.out.println("> Select event: ");
        final Event event = selectDomainObject(eventService, e -> e.getName());
        if (event == null) {
            System.err.println("No event found");
            return;
        }
        
        System.out.println("> Select air dates: ");
        final LocalDateTime airDate = selectAirDate(event.getAirDates());
        System.out.println("> Select seats: ");
        final Set<Long> seats = selectSeats(event, airDate);
        System.out.println("> Select user: ");
        final User userForBooking;
        User user = selectDomainObject(userService, u -> u.getFirstName() + " " + u.getLastName());
        if (user == null) {
            System.out.println("No user found. Input user info for booking: ");
            String email = readStringInput("Email: ");
            String firstName = readStringInput("First name: ");
            String lastName = readStringInput("Last name: ");
            userForBooking = new User();
            userForBooking.setEmail(email);
            userForBooking.setFirstName(firstName);
            userForBooking.setLastName(lastName);
        } else {
            userForBooking = user;
        }
        
        Set<Ticket> ticketsToBook = seats.stream().map(seat -> new Ticket(userForBooking, event, airDate, seat)).collect(Collectors.toSet());
        double price = bookingService.buyTicketsForEvent(event, airDate, user, seats);
        bookingService.bookTickets(ticketsToBook);
        System.out.println("Tickets booked! Total price: " + price);
    }

    private void getTicketsPrice() {
        System.out.println("> Select event: ");
        Event event = selectDomainObject(eventService, e -> e.getName());
        if (event == null) {
            System.err.println("No event found");
            return;
        }
        
        System.out.println("> Select air dates: ");
        LocalDateTime airDate = selectAirDate(event.getAirDates());
        System.out.println("> Select seats: ");
        Set<Long> seats = selectSeats(event, airDate);
        System.out.println("> Select user: ");
        User user = selectDomainObject(userService, u -> u.getFirstName() + " " + u.getLastName());
        if (user == null) {
            System.out.println("No user found");
        }
        
        double price = bookingService.buyTicketsForEvent(event, airDate, user, seats);
        printDelimiter();
        System.out.println("Price for tickets: " + price);
    }

    private Set<Long> selectSeats(Event event, LocalDateTime airDate) {
        Auditorium aud = event.getAuditoriums().get(airDate);

        Set<Ticket> tickets = bookingService.getPurchasedTicketsForEvent(event, airDate);
        List<Long> bookedSeats = tickets.stream().map(t -> t.getSeat()).collect(Collectors.toList());
        List<Long> freeSeats = aud.getAllSeats().stream().filter(seat -> !bookedSeats.contains(seat))
                .collect(Collectors.toList());

        System.out.println("Free seats: ");
        System.out.println(freeSeats);
        
        return inputSeats();
    }

    private Set<Long> inputSeats() {
        Set<Long> set = readInput("Input seats (comma separated): ", s -> 
            Arrays.stream(s.split(","))
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .boxed().collect(Collectors.toSet()));
        return set;
    }

    private LocalDateTime selectAirDate(NavigableSet<LocalDateTime> airDates) {
        List<LocalDateTime> list = airDates.stream().collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + formatDateTime(list.get(i)));
        }
        int dateIndex = readIntInput("Input air date index: ", list.size()) - 1;
        return list.get(dateIndex);
    }

    private <T extends DomainObject> T selectDomainObject(IAbstractDomainObjectService<T> service, Function<T, String> displayFunction) {
        if (!service.getAll().isEmpty()) {
            service.getAll().forEach(obj -> System.out.println("[" + obj.getId() + "] " + displayFunction.apply(obj)));
            long id = readIntInput("Input id (-1 for nothing): ");
            return service.getById(id);
        } else {
            return null;
        }
    }

}

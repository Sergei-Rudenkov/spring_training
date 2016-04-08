package ua.epam.spring.hometask.ui.console.state;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.service_interfaces.IAuditoriumService;

/**
 * State for managing auditoriums
 * 
 * @author Yuriy_Tkach
 */
@Component
public class AuditoriumManageState extends AbstractState {

    @Autowired
    private IAuditoriumService auditoriumService;

    @Override
    protected int printMainActions() {
        System.out.println(" 1) Search auditorium by name");
        System.out.println(" 2) View all");
        return 2;
    }

    @Override
    protected void runAction(int action) {
        switch (action) {
        case 1:
            searchAuditorium();
            break;
        case 2:
            printDefaultInformation();
            break;
        default:
            System.err.println("Unknown action");
        }
    }

    private void searchAuditorium() {
        String searchTerm = readStringInput("Input auditorium name: ");
        Auditorium a = auditoriumService.getByName(searchTerm);
        if (a == null) {
            System.out.println("Not found (searched for: " + searchTerm + ")");
        } else {
            printAuditorium(a);
        }
    }

    @Override
    protected void printDefaultInformation() {
        System.out.println("All auditoriums:");
        Set<Auditorium> all = auditoriumService.getAll();
        all.forEach(a -> printAuditorium(a));
    }

    private void printAuditorium(Auditorium a) {
        System.out.println(a.getName() + ", " + a.getNumberOfSeats() + " seats, vips: " + a.getVipSeats());
    }

}

package wo_spring_realization;

/**
 * Created by Sergei_Rudenkov on 10/28/2015.
 */
public class Client {

    private String id;
    private String fullName;
    private String greeting;

    public Client(String id, String fullName) {
        this.fullName = fullName;
        this.id = id;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}

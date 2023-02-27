
import java.util.*;
public class Reservation {
    private String name;
    private int numberOfGuests;
    private Date date;

    public Reservation(String name, int numberOfGuests, Date date) {
        this.name = name;
        this.numberOfGuests = numberOfGuests;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public Date getDate() {
        return date;
    }
}

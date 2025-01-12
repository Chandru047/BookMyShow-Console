import java.time.LocalDate;
import java.util.ArrayList;

public class User_POJO
{
    private String id ;
    private String pass;
    private String name;
    private String location;
    private LocalDate date;
    private ArrayList<Tickets> ticket = new ArrayList<>();



    public String getId()
    {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }


    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }


    public String getName()
    {
        return name;
    }

    public void setname(String name) {

        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }


    public void setPreferredDate(LocalDate parsedDate)
    {
        this.date = parsedDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Tickets> getTicket() {
        return ticket;
    }

    public void setTicket(ArrayList<Tickets> ticket) {
        this.ticket = ticket;
    }
}

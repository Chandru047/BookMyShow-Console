import java.util.ArrayList;

public class User_POJO
{
    private String id ;// String variable to store the id of the user
    private String pass;// String variable to store the pass of the user
    private String name;// String variable to store the name of the user
    private String location;// String variable to store the location of the user

    private ArrayList<Tickets> ticket = new ArrayList<>(); // ArrayList of ticket to store the tickets

    //getters and setters

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {

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

    public void setname(String name)
    {

        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {

        this.location = location;
    }

    public ArrayList<Tickets> getTicket()
    {
        return ticket;
    }

}

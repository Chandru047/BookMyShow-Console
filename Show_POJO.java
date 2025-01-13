import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show_POJO
{
    private LocalTime startTime; // variable to store the startTime of the show
    private LocalTime endTime;// variable to store the endTime of the show
    private LocalDate date;// variable to store the date of the show
    private ScreenPOJO screen;// variable to store the screen object
    private  long price; // variable to store the price of show(ticket)
    private  HashMap<Character, ArrayList<String>> seat;// Hashmap to store the seatingArrangment

    //Constructor
    Show_POJO(LocalTime startTime , LocalTime endTime , LocalDate date , ScreenPOJO screen , long price , HashMap<Character, ArrayList<String>> seat)
    {
        this.startTime = startTime ;
        this.endTime = endTime ;
        this.date = date ;
        this.screen = screen;
        this.price = price;
        this.seat = seat;
    }

    //Getters and Setters

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getDate() {
        return date;
    }


    //Overriding equals method from object class
    @Override
    public boolean equals(Object object)//called when using add() or contains() method
    {
        if (object == null || getClass() != object.getClass()) // if the given object is null or not the Show type
        {
            return false;
        }
        Show_POJO show = (Show_POJO) object; // cast the object to Show
        return Objects.equals(this.startTime , show.startTime) && Objects.equals(this.endTime , show.endTime); // return the boolean
    }

    //Overriding hashcode method from object class
    @Override // gives an unique hashCode considering startTime , endTime , date
    public int hashCode() //called when using add() or contains() method
    {
        return Objects.hash(startTime,endTime,date);
    }

    @Override
    public String toString() {
        return startTime.toString() ;
    }

    public ScreenPOJO getScreen() {
        return screen;
    }

    public void setScreen(ScreenPOJO screen) {
        this.screen = screen;
    }

    public long getPrice() {
        return price;
    }

    public HashMap<Character, ArrayList<String>> getSeatingArrangement() {
        return seat;
    }
}

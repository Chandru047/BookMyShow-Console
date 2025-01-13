import java.time.LocalTime;
import java.util.ArrayList;

public class Tickets
{
    private String theatreName;// String variable to store the theatre Name
    private String movieName;// String variable to store the movie Name
    private String screenName;// String variable to store the screen Name
    private String location;// String variable to store the theatre location
    private LocalTime startTime;// String variable to store the start time of the movie
    private  long duration;// String variable to store the duration of the movie
    private ArrayList<String> seats; // arrayList of seats to store the booked Seats
    private  long price; // long variable to store the total cost

    //Constructor
    public Tickets(String theatreName, String movieName, String screenName, String location, LocalTime startTime,long duration , ArrayList<String> seats , long price) {
        this.theatreName = theatreName;
        this.movieName = movieName;
        this.screenName = screenName;
        this.location = location;
        this.startTime = startTime;
        this.duration = duration;
        this.seats = seats;
        this.price = price;
    }

    //Getters and Setters
    public String getTheatreName() {
        return theatreName;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getLocation() {
        return location;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public ArrayList<String> getSeats() {
        return seats;
    }

    public long getDuration() {
        return duration;
    }

    public long getPrice() {
        return price;
    }
}

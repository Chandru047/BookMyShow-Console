import java.time.LocalTime;
import java.util.ArrayList;

public class Tickets
{
    private String theatreName;
    private String movieName;
    private String screenName;
    private String location;
    private LocalTime startTime;
    private  long duration;
    private ArrayList<String> seats;
    private  long price;

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

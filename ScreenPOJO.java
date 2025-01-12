import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ScreenPOJO
{
    private String screenName;
    private long noOfSeats;
    private HashMap<Character , ArrayList<String>> seatingArrangement = new HashMap<>();
    private HashSet<Show_POJO> shows = new HashSet<>();
    ScreenPOJO(String screenName , long noOfSeats , HashMap<Character , ArrayList<String>> seatingArrangement)
    {
        this.screenName = screenName ;
        this.noOfSeats = noOfSeats ;
        this.seatingArrangement = seatingArrangement;

    }

    String getScreenName()
    {
        return screenName ;
    }

    long getNoOfSeats()
    {
        return noOfSeats;
    }

    public   HashMap<Character, ArrayList<String>> getSeatingArrangement()
    {
        return seatingArrangement;
    }

    public HashSet<Show_POJO> getShows() {
        return shows;
    }

    public void setShows(HashSet<Show_POJO> shows) {
        this.shows = shows;
    }
}
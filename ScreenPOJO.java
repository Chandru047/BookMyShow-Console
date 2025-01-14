import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ScreenPOJO
{
    private String screenName; // variable to store the name of the screen
    private long noOfSeats;// variable to store the no of seats
    private HashMap<Character , ArrayList<String>> seatingArrangement ; // Hashmap to store the seating Arrangement
    private String grid;
    private HashSet<Show_POJO> shows = new HashSet<>(); // Hashset to store the show objects

    //Constructor
    ScreenPOJO(String screenName , long noOfSeats , HashMap<Character , ArrayList<String>> seatingArrangement , String grid)
    {
        this.screenName = screenName ;
        this.noOfSeats = noOfSeats ;
        this.seatingArrangement = seatingArrangement;
        this.grid = grid;
    }

    //Getters and Setters

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

    public String getGrid()
    {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }
}
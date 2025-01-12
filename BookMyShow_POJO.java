import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow_POJO
{
    private static final ArrayList<Admin_POJO> adminList= new ArrayList<>(); // ArrayList to store the admin objects
    private static final ArrayList<User_POJO> userList = new ArrayList<>(); // ArrayList to store the user object
    private static ArrayList<Theatre_POJO> theatres = new ArrayList<>(); // ArrayList to store the theatres

    private static HashMap<String , Theatre_POJO> theatreNameObj = new HashMap<>(); // HashMap to store the theatre name as key and object as value
    private static HashMap<String , ArrayList<Movie>> movieNameObj = new HashMap<>(); // HashMap to store the movie name as key and object as value

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // set the time format
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // set the date format

    // Getters and Setters


    static ArrayList<Admin_POJO> getAdminList()
    {
        return adminList;
    }

    static ArrayList<User_POJO> getUserList()
    {
        return userList;
    }

    public static DateTimeFormatter getDateFormatter()
    {
        return dateFormatter;
    }

    public static DateTimeFormatter getTimeFormatter()
    {
        return timeFormatter ;
    }

    public static HashMap<String,Theatre_POJO> getTheatreNameObj() {
        return theatreNameObj;
    }

    public static void setTheatreNameObj(HashMap<String,Theatre_POJO> theatreNameObj) {
        BookMyShow_POJO.theatreNameObj = theatreNameObj;
    }

    public static HashMap<String, ArrayList<Movie>> getMovieNameObj() {
        return movieNameObj;
    }

    public static void setMovieNameObj(HashMap<String, ArrayList<Movie>> movieNameObj) {
        BookMyShow_POJO.movieNameObj = movieNameObj;
    }

    public static ArrayList<Theatre_POJO> getTheatres() {
        return theatres;
    }

    public static void setTheatres(ArrayList<Theatre_POJO> theatres) {
        BookMyShow_POJO.theatres = theatres;
    }
}

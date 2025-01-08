import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow_POJO
{
    private static final ArrayList<Admin_POJO> adminList= new ArrayList<>();
    private static final ArrayList<User_POJO> userList = new ArrayList<>();
    private static ArrayList<Theatre_POJO> theatres = new ArrayList<>();

    private static HashMap<String , Theatre_POJO> theatreNameObj = new HashMap<>();
    private static HashMap<String , ArrayList<Movie>> movieNameObj = new HashMap<>();

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


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

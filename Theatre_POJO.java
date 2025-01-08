import java.util.ArrayList;
import java.util.HashMap;

public class Theatre_POJO
{
    private String theatreName;
    private String location;
    private ArrayList<ScreenPOJO> screen = new ArrayList<>();
    private HashMap<String , ScreenPOJO > screenNameObj= new HashMap<>();
    private ArrayList <Movie> movie = new ArrayList<>();

    public Theatre_POJO(String name, HashMap<String,ScreenPOJO> screenNameAndObject, String location)
    {
        this.theatreName = name;
        this.screenNameObj = screenNameAndObject;
        //this.movies = movies;
        this.location = location;
    }

    public Theatre_POJO() {

    }


    public String getTheatreName()
    {
        return theatreName;
    }

    public void setTheatreName(String theatreName)
    {
        this.theatreName = theatreName;
    }

    public String getTheatreLocation()
    {
        return location;
    }

    public void setTheatreLocation(String location)
    {
        this.location = location;
    }

    public ArrayList<ScreenPOJO> getScreen() {
        return screen;
    }

    public void setScreen(ArrayList<ScreenPOJO> screen) {
        this.screen = screen;
    }

    public ArrayList<Movie> getMovie() {
        return movie;
    }

    public void setMovie(ArrayList<Movie> movie) {
        this.movie = movie;
    }

    public HashMap<String,ScreenPOJO> getScreenNameObj() {
        return screenNameObj;
    }

    public void setScreenNameObj(HashMap<String,ScreenPOJO> screenNameObj) {
        this.screenNameObj = screenNameObj;
    }
}

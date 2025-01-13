import java.time.LocalDate;

public class Movie {
    private  Show_POJO show;
    private ScreenPOJO screen;
    private Theatre_POJO theatre; //
    private long duration; // variable to store the duration of the movie
    private String movieName ; // variable to store the movieName
    private LocalDate startDate ; // variable to store the screening date
    private String location ; // variable to store the location

    //Constructor
    public Movie(String movieName, String location, LocalDate startDate, long duration, Theatre_POJO theatres, ScreenPOJO screen, Show_POJO show)
    {
        this.movieName = movieName;
        this.location = location;
        this.startDate = startDate;
        this.duration = duration;
        this.theatre = theatres;
        this.screen = screen;
        this.show = show;
    }


    //Getters and Setters
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Theatre_POJO getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre_POJO theatre) {
        this.theatre = theatre;
    }

    public ScreenPOJO getScreen() {
        return screen;
    }

    public void setScreen(ScreenPOJO screen) {
        this.screen = screen;
    }

    public Show_POJO getShow() {
        return show;
    }
}

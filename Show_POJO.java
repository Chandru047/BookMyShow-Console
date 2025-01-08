import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Show_POJO
{
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String movieName;
    Show_POJO(LocalTime startTime , LocalTime endTime , LocalDate date , String movieName)
    {
        this.startTime = startTime ;
        this.endTime = endTime ;
        this.date = date ;
        this.movieName = movieName ;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMovieName() {
        return movieName;
    }
}

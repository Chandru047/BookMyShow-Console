import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Show_POJO
{
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private ScreenPOJO screen;
    private  long price;

    Show_POJO(LocalTime startTime , LocalTime endTime , LocalDate date , ScreenPOJO screen , long price)
    {
        this.startTime = startTime ;
        this.endTime = endTime ;
        this.date = date ;
        this.screen = screen;
        this.price = price;
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


    @Override
    public boolean equals(Object object)
    {
        if (object == null || getClass() != object.getClass())
        {
            return false;
        }
        Show_POJO show = (Show_POJO) object;
        return Objects.equals(this.startTime , show.startTime) && Objects.equals(this.endTime , show.endTime);
    }

    @Override
    public int hashCode() {
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
}

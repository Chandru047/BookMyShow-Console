import java.util.ArrayList;
import java.util.HashMap;

public class Theatre_POJO
{
    private String theatreName;// String variable to store the theatre Name
    private String location;// String variable to store the theatre location

    private HashMap<String , ScreenPOJO > screenNameObj; // //Hashmap to store the screen name and its object

    //Constructor
    public Theatre_POJO(String name, HashMap<String,ScreenPOJO> screenNameAndObject, String location)
    {
        this.theatreName = name;
        this.screenNameObj = screenNameAndObject;
        this.location = location;
    }

    //Getters and setters

    public String getTheatreName()
    {
        return theatreName;
    }

    public String getTheatreLocation()
    {
        return location;
    }

    public HashMap<String,ScreenPOJO> getScreenNameObj() {
        return screenNameObj;
    }

}

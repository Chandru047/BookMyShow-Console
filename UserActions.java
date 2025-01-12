import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserActions
{
    static Scanner in = new Scanner(System.in);
    static void register(Scanner in, String id)
    {
        User_POJO user = new User_POJO();
        user.setId(id);
        System.out.println("Enter the Password:");
        String password = in.nextLine();
        user.setPass(password);
        System.out.println("Enter your Name:(Default : " + id + ")");
        String name = in.nextLine();
        if (name.isEmpty())
        {
            user.setname(id);
        }
        else
        {
            user.setname(name);
        }
        System.out.println("Enter your Location:");
        String location = in.nextLine();
        user.setLocation(location);
        System.out.println("Account Created Successfully");
        BookMyShow_POJO.getUserList().add(user);

    }

    User_POJO login(Scanner in, String id)
    {
        for (int i = 0; i < BookMyShow_POJO.getUserList().size(); i++)
        {
            if (BookMyShow_POJO.getUserList().get(i).getId().equals(id))
            {
                System.out.println("Enter the password:");
                String password = in.nextLine();
                if (BookMyShow_POJO.getUserList().get(i).getPass().equals(password))
                {
                    System.out.println("Login successful!");
                    return BookMyShow_POJO.getUserList().get(i);
                }
                else
                {
                    System.out.println("Invalid password. Try again.");
                    return null;
                }
            }
        }

        System.out.println("User does not exist. Creating a new account...");
        return null;
    }


static void availableMovies(User_POJO currentUser)
{
    ArrayList<Movie> moviess = new ArrayList<>();
    System.out.println("-------------------------------");
    LocalDate currentDate;
        // Assuming the user's location
    if (currentUser.getDate() == null)
    {
        currentDate = LocalDate.now();
    }
    else
    {
        currentDate = currentUser.getDate();
    }
    String location = currentUser.getLocation();

    System.out.println("Movies Currently available in your Location: " + location);

    for (String movieName : BookMyShow_POJO.getMovieNameObj().keySet())
    {
        ArrayList<Movie> movies = BookMyShow_POJO.getMovieNameObj().get(movieName);

        for (Movie movie : movies)
        {

            if (movie.getLocation().equalsIgnoreCase(location))
            {
                LocalDate movieDate = movie.getStartDate();

                if (currentDate.equals(movieDate))
                {
                    System.out.println(" - " + movie.getMovieName());
                }
            }
        }
    }
    System.out.println("-------------------------------");

    System.out.println("Would you like to  change the (Date or Location) (Y/N) :");
    String choice = in.nextLine();
    if (choice.equals("Y"))
    {
        changeLocationOrDate(currentUser);
        currentDate = currentUser.getDate();
        for (String movieName : BookMyShow_POJO.getMovieNameObj().keySet())
        {
            ArrayList<Movie> movies = BookMyShow_POJO.getMovieNameObj().get(movieName);

            for (Movie movie : movies)
            {

                if (movie.getLocation().equalsIgnoreCase(location))
                {
                    LocalDate movieDate = movie.getStartDate();

                    if (currentDate.equals(movieDate))
                    {
                        System.out.println(" - " + movie.getMovieName());
                    }
                }
            }
        }

    }
    System.out.println("Enter the Movie name to book : ");
    String currentMovie = in.nextLine();
    for(var movieObject : BookMyShow_POJO.getMovieNameObj().get(currentMovie))
    {
        if(movieObject.getLocation().equals(currentUser.getLocation()) && movieObject.getStartDate().isEqual(currentDate))
        {
            moviess.add(movieObject);
        }
    }
    showDetails(currentUser , moviess);
}


static void changeLocationOrDate(User_POJO currentUser) {
    // Ask the user if they want to change the location or date
    System.out.println("Do you want to change:");
    System.out.println("1. Location");
    System.out.println("2. Date");
    System.out.print("Enter your choice (1 or 2): ");
    int choice = Integer.parseInt(in.nextLine());

    if (choice == 1)
    {
    // Change Location logic
    System.out.println("Your Current Location: " + currentUser.getLocation());

     System.out.println("Available Locations:");
     var availableLocations = new HashSet<>(); // Use a typed HashSet for type safety

    // Get all locations from TheatreNameObj()
    for (Theatre_POJO theatre : BookMyShow_POJO.getTheatreNameObj().values())
    {
        availableLocations.add(theatre.getTheatreLocation());
    }

    for (var location : availableLocations)
    {
        System.out.println(" - " + location);
    }

    System.out.print("Enter your new location: ");
    String newLocation = in.nextLine();

    if (availableLocations.contains(newLocation))
    {
        currentUser.setLocation(newLocation); // Set the new location
        System.out.println("Location changed successfully to: " + newLocation);
    }
    else
    {
        System.out.println("Invalid location. Please try again.");
    }

    }
    else if (choice == 2)
    {
        // Change Date logic
        System.out.println("Enter your new preferred date : ");
        LocalDate newDate = LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter());

        currentUser.setPreferredDate(newDate); // Assuming User_POJO has setPreferredDate method
        System.out.println("Date changed successfully to: " + newDate);

    }
    else
    {
        System.out.println("Invalid choice. Please select 1 for location or 2 for date.");
    }

    }

static void showDetails(User_POJO currentUser , ArrayList<Movie> movies)
{
    System.out.println("Currently Available Theatres");
    HashMap<String,HashSet<Show_POJO>> ShowsAtTheatre = new HashMap<>();

    for(var movie : movies)
    {
        if(ShowsAtTheatre.containsKey(movie.getTheatre().getTheatreName()))
        {
            ShowsAtTheatre.get(movie.getTheatre().getTheatreName()).add(movie.getShow());
        }
        else
        {
            HashSet<Show_POJO> shows = new HashSet<>();
            shows.add(movie.getShow());
            ShowsAtTheatre.put(movie.getTheatre().getTheatreName(), shows);
        }
    }

    for(String theatreName : ShowsAtTheatre.keySet())
    {
            System.out.println("Theatre Name : "+theatreName);
            System.out.println("Shows : "+ShowsAtTheatre.get(theatreName).toString());
    }

    while (true)
    {
        System.out.println("Enter the theatre name : ");
        String theatreName = in.nextLine();
        System.out.println("Enter the show time : ");
        LocalTime showTime = LocalTime.parse(in.nextLine(),BookMyShow_POJO.getTimeFormatter());
        var shows = ShowsAtTheatre.get(theatreName);
        if(shows == null)
        {
            System.out.println("Enter correct theatre...");
        }
        Show_POJO currentShow = null;
        for(Show_POJO show : shows)
        {
            if(show.getStartTime().equals(showTime))
            {
                currentShow = show;
            }
        }
        if(currentShow==null)
        {
            System.out.println("Enter the correct time of show...");
        }
        else
        {
            System.out.println("Screen Name : "+currentShow.getScreen().getScreenName());
            System.out.println("No of  Seats : "+currentShow.getScreen().getNoOfSeats());
            var seatsAndGrids = currentShow.getScreen().getSeatingArrangement();
            System.out.println("            ----------------- Seating arrangement ---------------");
            for(var seats:seatsAndGrids.entrySet())
            {
                System.out.println(seats.getKey()+" "+seats.getValue());
            }
        }
        var seatsAndGrids = currentShow.getScreen().getSeatingArrangement();

        System.out.println("Enter the No Seats to Book");
        long seatCount = Long.parseLong(in.nextLine());
        long price = seatCount*currentShow.getPrice();
        ArrayList<String> temp = new ArrayList<>();
        while(seatCount > 0 )
        {
            System.out.println("Enter the Seat Number to book");
            String seat = in.nextLine();

            char row = seat.charAt(0);
            int seatNumber = (Integer.parseInt(seat.substring(1))) -1 ;
            ArrayList<String> seats = seatsAndGrids.get(row);
            if (seats.get(seatNumber).equals("X"))
            {
                System.out.println("------------------The Selected seat has been already booked-------------------");
                continue;
            }
            temp.add(seat);
            seats.set(seatNumber , "X");
            seatsAndGrids.put(row ,seats);
            for(var seatss:seatsAndGrids.entrySet())
            {
                System.out.println(seatss.getKey()+" "+seatss.getValue());
            }

            seatCount -- ;
        }
        System.out.println("Paying rupees " + price);
        Tickets ticket = new Tickets(theatreName ,movies.getFirst().getMovieName(), currentShow.getScreen().getScreenName() , currentUser.getLocation() , showTime ,movies.getFirst().getDuration(), temp , price);

        currentUser.getTicket().add(ticket);
        break;
    }
}

static void viewTicket(User_POJO currentUser)
{
    ArrayList<Tickets> ticket = currentUser.getTicket();
    for (Tickets tickets : ticket)
    {
        System.out.println("------------------------------");
        System.out.println("Theatre Name : " + tickets.getTheatreName());
        System.out.println("Theatre Location : " + tickets.getLocation());
        System.out.println("Movie Name : " + tickets.getMovieName());
        System.out.println("Screen Name : " + tickets.getScreenName());
        System.out.println("Show Time : " + tickets.getStartTime());
        System.out.println("Movie Duration :" + tickets.getDuration());
        System.out.println("Booked Seats : " + tickets.getSeats());
        System.out.println("Price : " + tickets.getPrice());
        System.out.println("------------------------------");

    }
}

}


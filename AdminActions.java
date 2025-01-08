import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminActions
{
    static Scanner in = new Scanner(System.in);
    Admin_POJO login(Scanner in, String id)
    {
        while (true)
        {
            int index = -1;

            // for loop to check if the user already exist
            for (int i = 0; i < BookMyShow_POJO.getAdminList().size(); i++)
            {
                if (BookMyShow_POJO.getAdminList().get(i).getId().equals(id))
                {
                    index = i; // if a user is found with a same id then change the index value
                    break;
                }
            }


            if (index != -1) // if the index remains unchanged it means the user already exist
            {
                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (BookMyShow_POJO.getAdminList().get(index).getPass().equals(password)) // if the password matches then get to admin operations
                {
                    System.out.println("Login successful!");
                    BookMyShow.adminOptions();
                    return BookMyShow_POJO.getAdminList().get(index);
                }
                else
                {
                    System.out.println("Invalid password. Try again.");
                    return null;
                }
            }

            else // if id does not exist then procedure to create a new admin account
            {
                System.out.println("ID does not exist. Creating a new account...");
                int enteredPin = 0;
                try
                {
                    System.out.println("Enter the pin: ");
                    enteredPin = Integer.parseInt(in.nextLine());
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid Input. Please enter a Numeric value");
                    return null;
                }


                if (enteredPin != Admin_POJO.getPIN())
                {
                    System.out.println("Incorrect pin. Account creation failed.");
                    return null;
                }


                Admin_POJO admin = new Admin_POJO();
                admin.setId(id);
                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();
                admin.setPass(newPassword);

                BookMyShow_POJO.getAdminList().add(admin);

                System.out.println("Account created successfully!");
                return admin;
            }
        }
    }

    static void addMovie()
    {
        while (true)
        {
            System.out.println("Enter the Movie name");
            String movieName = in.nextLine(); // get the name of the movie as input
            System.out.println("Enter the Location of the Movie");
            String location = in .nextLine(); // get the location to screen the movie
            boolean available = false ; // initialize a boolean value to check if location available
            for (String theatrePojo : BookMyShow_POJO.getTheatreNameObj().keySet()) // for loop to check if any theatre exist in the location
            {
                if (BookMyShow_POJO.getTheatreNameObj().get(theatrePojo).getTheatreLocation().equals(location)) // if the location already exists in theatre then change available to true
                {
                    available = true ;
                    break ;
                }

            }
            if (!available) // if no theatre is available in that location then exit out of addMovie logic
            {
                System.out.println("------------------ No Theatres Available in your Current Location --------------------");
                return;
            }

            System.out.println("Enter the date of the Movie : ");
            LocalDate date = LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter()); // get the date to screen the movie

            System.out.print("Enter the Duration of the movie : ");
            long duration = Long.parseLong(in.nextLine()); // get the duration of the movie

            for(String theatres:BookMyShow_POJO.getTheatreNameObj().keySet()) // for loop to go through the TheatreObj
            {
                if(BookMyShow_POJO.getTheatreNameObj().get(theatres).getTheatreLocation().equals(location)) // match the location
                {
                    System.out.println("* "+ theatres); // print all the theatres in that location
                }
            }

            System.out.println("Enter the Theatre name : ");
            String theatreName = in.nextLine(); // get the name of the theatre to screen the movie

            Theatre_POJO theatres = BookMyShow_POJO.getTheatreNameObj().get(theatreName); // get the object of the chosen theatre
            System.out.println("Available Screens..");
            for(String screens : theatres.getScreenNameObj().keySet()) // for loop to go through all the screens in the theatre
            {
                System.out.println(screens);
            }
            ScreenPOJO screen = null;
            System.out.print("Enter the screen name : ");
            String screenName = in.nextLine();


            for(String screens : theatres.getScreenNameObj().keySet())
            {
                if(screens.equals(screenName))
                {
                     screen = theatres.getScreenNameObj().get(screens);
                }
            }

            System.out.print("Enter the start time of the Show : ");
            LocalTime startTime = LocalTime.parse(in.nextLine(),BookMyShow_POJO.getTimeFormatter());
            LocalTime endTime = startTime.plusMinutes(duration + 30);

            Show_POJO Show = new Show_POJO(startTime,endTime,date,movieName);
            screen.getShows().add(Show);
            Movie currentMovie = new Movie(movieName,location,date,duration,theatres,screen,Show);

            var movieList = BookMyShow_POJO.getMovieNameObj().get(movieName);
            if(movieList==null)
            {
                movieList = new ArrayList<>();
            }
            for(Movie obj : movieList)
            {
                if(obj.getStartDate().isEqual(date) && obj.getDuration()==duration && obj.getLocation().equals(location) && obj.getTheatre().getTheatreName().equals(theatreName) && obj.getScreen().getScreenName().equals(screenName) && (startTime.isBefore(obj.getShow().getStartTime()) || startTime.isAfter(obj.getShow().getEndTime())) && (endTime.isBefore(obj.getShow().getStartTime()) || endTime.isAfter(obj.getShow().getEndTime())))
                {
                    System.out.println("Movie already exists..");
                    return;
                }
            }


            movieList.add(currentMovie);
            BookMyShow_POJO.getMovieNameObj().put(movieName,movieList);
            System.out.println("Movie added successfully ");
            break ;
        }



    }

    static void viewMovies()
    {
        var movie = BookMyShow_POJO.getMovieNameObj().keySet();
        for(var movies:movie)
        {
            System.out.println("Movie Name : "+movies);
            var availableMovies = BookMyShow_POJO.getMovieNameObj().get(movies);
            for(Movie movieObj : availableMovies)
            {
                System.out.println("---------------------");
                System.out.println("Theatre : " + movieObj.getTheatre().getTheatreName());
                System.out.println("Location : " + movieObj.getLocation());
                System.out.println("Date : " + movieObj.getStartDate().format(BookMyShow_POJO.getDateFormatter()));
                System.out.println("Show Start Time : " + movieObj.getShow().getStartTime().format(BookMyShow_POJO.getTimeFormatter()));
                System.out.println("Show End Time : " + movieObj.getShow().getEndTime().format(BookMyShow_POJO.getTimeFormatter()));
                System.out.println("---------------------");
            }
        }
    }

    static void addTheatre() // Method to add a Theatre
    {
        System.out.println("Enter the name of the Theatre");
        String theatreName = in.nextLine(); // get the theatre name as input
        System.out.println("Enter the Location : ");
        String location = in.nextLine(); // get the theatre location has input

        for (Theatre_POJO theatre : BookMyShow_POJO.getTheatreNameObj().values()) // for loop to iterate between all theatre objects
        {
            if (theatre.getTheatreName().equals(theatreName) && theatre.getTheatreLocation().equals(location)) // if condition to check if the theatre already exist in that location
            {
                System.out.println("Theatres  already exists in the location !");
                return;
            }
        }


        System.out.println("Enter the  Screen count :");
        int screenCount = Integer.parseInt(in.nextLine()); // get the number of screens as input
        HashMap<String,ScreenPOJO> screensHashMap = new HashMap<>();
        while (screenCount != 0) // while loop to get the details of all the available screens
        {
            System.out.println("Enter the name of the Screen ");
            String screenName = in.nextLine(); // get the name of the screen as input
            System.out.println("Enter the number of Seats in Screen " + screenName);
            long numberSeats =Long.parseLong(in.nextLine()); // get the number of seats as an input
            System.out.println("Enter the Screen Grid (eg:2*8*2)");
            String screenGrid = in.nextLine(); // get the grid layout as an input
            var grid = Utilities.generateSeatingPatterns(numberSeats, screenGrid); // get the seating arrangement form generateSeatingPatterns method in Utilities class
            ScreenPOJO screenPOJO = (new ScreenPOJO(screenName, numberSeats, grid)); // create a new screen object and store all the details
            screensHashMap.put(screenName , screenPOJO); // add it to the Hashmap
            screenCount--; // Decrement to iterate through the Screens
        }
        Theatre_POJO theatre = new Theatre_POJO(theatreName , screensHashMap , location); // creating a new theatre object
        BookMyShow_POJO.getTheatreNameObj().put(theatreName , theatre); // setting the theatre name as key and the value as theatre object
        System.out.println("Theatre added Successfully");
    }

    static void viewTheatres() // Method to view all the available theatres
    {
        System.out.println("---------Theatres Currently Available------------");

        // Loop through each theatre name
        for (String theatre : BookMyShow_POJO.getTheatreNameObj().keySet())
        {
            // Display theatre details
            System.out.println("Name : " + theatre); // get the theatre name
            var theatres = BookMyShow_POJO.getTheatreNameObj().get(theatre); // get the object stored in the theatre name key
            System.out.println("Location : " + theatres.getTheatreLocation()); // print the location

            // Display available screens in the theatre
            System.out.println("Available Screens ...");

            for (ScreenPOJO screenPOJO : theatres.getScreen()) // for loop to get all the screen details
            {
                System.out.println("Name of Screen : " + screenPOJO.getScreenName()); // get the name of the screen
                System.out.println("No of Seats : " + screenPOJO.getNoOfSeats()); // get the number of seats
                System.out.println("Seat Layout --> " + screenPOJO.getSeatingArrangement()); // get the seating arrangement
                System.out.println();
            }
            System.out.println("-----------------------------------------------");
        }
    }

}

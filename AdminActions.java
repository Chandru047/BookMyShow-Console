import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminActions
{
    static Scanner in = new Scanner(System.in); //static Scanner
    Admin_POJO login(Scanner in, String id)
    {
        while (true) // infinite loop for admin Login
        {
            int index = -1; // set a default value for the index variable

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
                String password = in.nextLine(); // get the password as an input

                if (BookMyShow_POJO.getAdminList().get(index).getPass().equals(password)) // if the password matches then get to admin operations
                {
                    System.out.println("Login successful!");
                    BookMyShow.adminOptions(); // if condition true then call adminOptions
                    return BookMyShow_POJO.getAdminList().get(index); // return the admin object
                }
                else // if the condition is false then return null
                {
                    System.out.println("Invalid password. Try again.");
                    return null;
                }
            }

            else // if id does not exist then procedure to create a new admin account
            {
                System.out.println("ID does not exist. Creating a new account...");
                int enteredPin = 0; // set a initial value for the enteredPin variable
                try // add a try block to handle exceptions
                {
                    System.out.println("Enter the pin: "); // get the pin as an input
                    enteredPin = Integer.parseInt(in.nextLine());
                }
                catch (NumberFormatException e) // if an exception occur then catch it and return null
                {
                    System.out.println("Invalid Input. Please enter a Numeric value");
                    return null;
                }


                if (enteredPin != Admin_POJO.getPIN()) // if the pin does not match then return null
                {
                    System.out.println("Incorrect pin. Account creation failed.");
                    return null;
                }


                Admin_POJO admin = new Admin_POJO(); // create a new object for Admin_POJO
                admin.setId(id); // set the id to the object
                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine(); // store the password
                admin.setPass(newPassword); // set the password to the object

                BookMyShow_POJO.getAdminList().add(admin); // add the object to teh arrayList in BookMyShowPojo

                System.out.println("Account created successfully!");
                return admin; // return the admin object
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
                if (BookMyShow_POJO.getTheatreNameObj().get(theatrePojo).getTheatreLocation().equals(location)) // check  if the location already exists in theatre
                {
                    available = true ; // change available to true
                    break ; // break the for loop
                }

            }
            if (!available) // if no theatre is available in that location then exit out of addMovie logic
            {
                System.out.println("------------------ No Theatres Available in your Current Location --------------------");
                return; // get out of the method
            }

            System.out.println("Enter the date of the Movie (dd-MM-yyyy) : ");
            LocalDate date = LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter()); // get the date to screen the movie

            System.out.print("Enter the Duration of the movie (in minutes) : ");
            long duration = Long.parseLong(in.nextLine()); // get the duration of the movie

            System.out.println("Enter the price of a single ticket");
            long price = Integer.parseInt(in.nextLine());

            // Display theatres in the given location
            System.out.println("Available Theatres:");
            for(String theatres:BookMyShow_POJO.getTheatreNameObj().keySet()) // for loop to go through the TheatreObj
            {
                if(BookMyShow_POJO.getTheatreNameObj().get(theatres).getTheatreLocation().equals(location)) // match the location
                {
                    System.out.println(theatres); // print all the theatres in that location
                }
            }

            System.out.println("Enter the Theatre name : ");
            String theatreName = in.nextLine(); // get the name of the theatre to screen the movie
            boolean exist = false; // set the initial value as false for exist variable
            for (var name : BookMyShow_POJO.getTheatreNameObj().keySet()) // iterate through the Theatre Hashmap (KeySet)
            {
                if (BookMyShow_POJO.getTheatreNameObj().get(name).getTheatreName().equals(theatreName)) // if the entered theatre exists
                {
                    exist = true ; // set the exist variable to true
                }
            }
            if (!exist) // if the theatre does not exist
            {
                System.out.println("Enter a valid Theatre !!");
                continue; // start from the beginning of the while Loop
            }
            Theatre_POJO theatres = BookMyShow_POJO.getTheatreNameObj().get(theatreName); // get the object of the chosen theatre

            System.out.println("Available Screens..");
            for(String screens : theatres.getScreenNameObj().keySet()) // for loop to go through all the screens in the theatre
            {
                System.out.println(screens); // print all the Screens
            }
            ScreenPOJO screen = null; // initialize the screen variable of type ScreenPojo as null
            System.out.print("Enter the name of the Screen : ");
            String screenName = in.nextLine(); // get the name of the screen as input


            for(String screens : theatres.getScreenNameObj().keySet()) // go through all the Screen
            {
                if(screens.equals(screenName)) // if the Screen name matches
                {
                     screen = theatres.getScreenNameObj().get(screens); // store the object in the screen variable
                     break; // break the for loop
                }
            }

            if (screen == null) // if the screen is null then the screen is not visible
            {
                System.out.println("Invalid screen name!");
                continue; // go back to the top of the loop
            }

            System.out.print("Enter the start time of the Show : ");
            LocalTime startTime = LocalTime.parse(in.nextLine(),BookMyShow_POJO.getTimeFormatter()); // get the start time of the movie from the admin
            LocalTime endTime = startTime.plusMinutes(duration + 30); // calculate the end time
            for (var show : screen.getShows()) // get through the shows
            {
                if (startTime.isBefore(show.getStartTime()) || endTime.isBefore(show.getEndTime()) && (startTime.isAfter(show.getEndTime()) || endTime.isAfter(show.getEndTime())) && date.isEqual(show.getDate()))
                {
                    System.out.println("Show already exist ");
                    return; // exit out of the method
                }
            }
            Show_POJO Show = new Show_POJO(startTime,endTime,date,screen,price); // create a new Screen object and pass the details of the screen
            if(screen.getShows().contains(Show)) // if the show already exist
            {
                System.out.println("The provided show already exist");
                return; // exit out of the method
            }
            screen.getShows().add(Show); // add the show to the show HashMap in the Screen
            Movie currentMovie = new Movie(movieName,location,date,duration,theatres,screen,Show); // create a new Movie object and pass the details

            var movieList = BookMyShow_POJO.getMovieNameObj().get(movieName); // get the object of the movie
            if(movieList==null) // if null then there is no movies available
            {
                movieList = new ArrayList<>(); // Initialize the movieList arrayList to avoid null pointer exception
            }


            movieList.add(currentMovie); // add the movie to the movieList(ArrayList)
            BookMyShow_POJO.getMovieNameObj().put(movieName,movieList); // set the theatre name as key and the arraylist of object as value
            System.out.println("Movie added successfully ");
            break ; // break the while loop
        }
    }

    static void viewMovies()
    {
        if(BookMyShow_POJO.getMovieNameObj().isEmpty()) // Go through all the movie object
        {
            System.out.println("No Movies Available ....");
            return; // if no movie is available then exit the method
        }

        var movie = BookMyShow_POJO.getMovieNameObj().keySet(); // get all the keySet of the Movie Hashmap
        for(var movies:movie) // go through all the KeySet
        {
            System.out.println("Movie : "+movies); // print the name of the movie
            var availableMovies = BookMyShow_POJO.getMovieNameObj().get(movies);  // get the list of Movie objects for the given movie name
            for(Movie movieObj : availableMovies) // Iterate over the list of Movie objects
            {
                System.out.println("---------------------");
                System.out.println("  - Theatre : " + movieObj.getTheatre().getTheatreName()); // print the name of the theatre
                System.out.println("  - Location : " + movieObj.getLocation()); // print the location of the theatre
                System.out.println("  - Date : " + movieObj.getStartDate().format(BookMyShow_POJO.getDateFormatter())); // print the date of the movie
                System.out.println("  - Start Time : " + movieObj.getShow().getStartTime().format(BookMyShow_POJO.getTimeFormatter())); // print the start time of the movie
                System.out.println("  - End Time : " + movieObj.getShow().getEndTime().format(BookMyShow_POJO.getTimeFormatter())); // print the end time of  the movie
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
                return; // if the theatre already exist then exit from the method
            }
        }


        System.out.println("Enter the  Screen count :");
        int screenCount = Integer.parseInt(in.nextLine()); // get the number of screens as input
        HashMap<String,ScreenPOJO> screensHashMap = new HashMap<>(); // HashMap with Name of the Screen as Key , ScreenPojo
        while (screenCount != 0) // while loop to get the details of all the available screens
        {
            System.out.println("Enter the name of the Screen ");
            String screenName = in.nextLine(); // get the name of the screen as input
            System.out.println("Enter the number of Seats in Screen " + screenName);
            long numberSeats =Long.parseLong(in.nextLine()); // get the number of seats as an input
            System.out.println("Enter the Screen Grid (eg:2*8*2)");
            String screenGrid = in.nextLine(); // get the grid layout as an input
            var grid = Utilities.generateSeatingPatterns(numberSeats, screenGrid); // get the seating arrangement form generateSeatingPatterns method in Utilities class
            if (grid == null) // if the grid is not created successfully then start the while loop again
            {
                continue;
            }
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
            System.out.println("-------------------------------------------------");
            var theatres = BookMyShow_POJO.getTheatreNameObj().get(theatre); // get the object stored in the theatre name key
            System.out.println("Location : " + theatres.getTheatreLocation()); // print the location

            // Display available screens in the theatre
            System.out.println("Available Screens ...");

            for (String screenName : theatres.getScreenNameObj().keySet()) // for loop to get all the screen details
            {
                ScreenPOJO screenPOJO = theatres.getScreenNameObj().get(screenName);
                System.out.println("Name of Screen : " + screenPOJO.getScreenName()); // get the name of the screen
                System.out.println("No of Seats : " + screenPOJO.getNoOfSeats()); // get the number of seats
                System.out.println("Seat Layout : ");
                for(var seats:screenPOJO.getSeatingArrangement().keySet())
                {
                    System.out.println(seats + " " + screenPOJO.getSeatingArrangement().get(seats));
                }// get the seating arrangement
                System.out.println();
            }
            System.out.println("-----------------------------------------------");
        }
    }

}

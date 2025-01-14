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

    static void addMovie() {
        while (true) {
            System.out.println("Enter the Movie name");
            String movieName = in.nextLine(); // get the name of the movie to add

            System.out.println("Enter the Location of the Movie");
            String location = in.nextLine(); // get the location to add the movie

            boolean available = false; // initialize an boolean value
            for (Theatre_POJO theatre : BookMyShow_POJO.getTheatreNameObj().values()) // go through the theatre objects
            {
                if (theatre.getTheatreLocation().equals(location)) // check if the theatres location matches the provided location
                {
                    available = true; // if matches then change the boolean to trur
                    break; // after a match if found exit of the loop
                }
            }

            if (!available) // if no theatre available
            {
                System.out.println("------------------ No Theatres Available in your Current Location --------------------");
                return; // exit out of the method
            }

            System.out.println("Enter the date of the Movie (dd-MM-yyyy): "); // get the date to screen the movie
            LocalDate date;
            try // try to get an date input
            {
                date = LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter());
            }
            catch (Exception e) // if an exception occurs then catch it
            {
                System.out.println("Invalid date format!");
                continue; // go to the beginning of the while loop
            }

            System.out.print("Enter the Duration of the movie (in minutes): "); // get the duration of the movie
            long duration;
            try // try to get the duration
            {
                duration = Long.parseLong(in.nextLine());
            }
            catch (NumberFormatException e)// if an exception occurs then catch it
            {
                System.out.println("Invalid duration!");
                continue;// go to the beginning of the while loop
            }

            System.out.println("Enter the price of a single ticket"); // get the price of the movie
            long price; // try to get the price
            try
            {
                price = Long.parseLong(in.nextLine());
            }
            catch (NumberFormatException e)// if an exception occurs then catch it
            {
                System.out.println("Invalid ticket price!");
                continue; // go to the beginning of the while loop
            }

            System.out.println("Available Theatres:");
            for (Theatre_POJO theatre : BookMyShow_POJO.getTheatreNameObj().values()) // go through all the theatre objects
            {
                if (theatre.getTheatreLocation().equals(location)) // if the location matches
                {
                    System.out.println(theatre.getTheatreName()); // print the name of the theatre
                }
            }

            System.out.println("Enter the Theatre name: ");
            String theatreName = in.nextLine(); // get the preferred theatre name from the available theatres
            Theatre_POJO theatres = BookMyShow_POJO.getTheatreNameObj().get(theatreName); // get that theatre object and store it in theatres variable
            if (theatres == null || !theatres.getTheatreLocation().equals(location))  // if no theatre exist or the theatre location does not match
            {
                System.out.println("Invalid theatre name!");
                continue; // go to the beginning of the while loop
            }

            System.out.println("Available Screens:");
            for (String screenName : theatres.getScreenNameObj().keySet()) // go through all the screen keys
            {
                System.out.println(screenName); // print the key (Screen Name)
            }

            System.out.print("Enter the name of the Screen: ");
            String screenName = in.nextLine();// get the name of the screen as input
            ScreenPOJO screen = theatres.getScreenNameObj().get(screenName);// get that screen object
            if (screen == null) // if the name does not match
            {
                System.out.println("Invalid screen name!");
                continue;// go to the beginning of the while loop
            }

            System.out.print("Enter the start time of the Show (HH:mm): ");
            LocalTime startTime;
            try // get the start time of the movie
            {
                startTime = LocalTime.parse(in.nextLine(), BookMyShow_POJO.getTimeFormatter());
            }
            catch (Exception e) // if an exception occurs then catch it
            {
                System.out.println("Invalid time format!");
                continue;// go to the beginning of the while loop
            }

            LocalTime endTime = startTime.plusMinutes(duration + 30); // calculate the end time
            boolean overlaps = false; // initialize the boolean value
            for (Show_POJO show : screen.getShows()) // get the show in the screen
            {
                // Check if the date of the new show is the same as the existing show’s date
                // check if the new show ends before the existing show starts
                // check if the new show starts after the existing show ends
                if (date.isEqual(show.getDate()) && !(endTime.isBefore(show.getStartTime()) || startTime.isAfter(show.getEndTime()))) {
                    overlaps = true; // change the boolean to true
                    break; // break the loop
                }
            }

            if (overlaps)  // if overlap true
            {
                System.out.println("Show overlaps with an existing one.");
                return; // exit out of the method
            }
            HashMap<Character, ArrayList<String>> clone = new HashMap<>(); // HashMap to copy the SeatingArrangement
            for (var entry : screen.getSeatingArrangement().entrySet()) // get the key and values of the existing Hashmap
            {
                char key = entry.getKey(); // store the key
                ArrayList<String> value = entry.getValue(); // store the value in arrayList of String

                // Deep copy the ArrayList
                ArrayList<String> clonedList = new ArrayList<>(value);
                clone.put(key, clonedList); // put the key and the String ArrayList
            }

            Show_POJO show = new Show_POJO(startTime, endTime, date, screen, price , clone); // create an show object and add the details of the show
            screen.getShows().add(show); // add the object to the screen

            Movie currentMovie = new Movie(movieName, location, date, duration, theatres, screen, show); // create an movie object and store the details of the movie
            if (!BookMyShow_POJO.getMovieNameObj().containsKey(movieName))  // if the movie not exist
            {
                BookMyShow_POJO.getMovieNameObj().put(movieName, new ArrayList<>()); // put the key and an empty arraylist
            }
            BookMyShow_POJO.getMovieNameObj().get(movieName).add(currentMovie); // add the movie object to that key

            System.out.println("Movie added successfully!");
            break; // break the for loop
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
            ScreenPOJO screenPOJO = (new ScreenPOJO(screenName, numberSeats, grid , screenGrid)); // create a new screen object and store all the details
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

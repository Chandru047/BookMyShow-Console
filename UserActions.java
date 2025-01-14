import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserActions
{
    static Scanner in = new Scanner(System.in);
    // method to register the user
    static void register(Scanner in, String id)
    {
        User_POJO user = new User_POJO(); // create an user object
        user.setId(id); // set the id in the object
        System.out.println("Enter the Password:");
        String password = in.nextLine();//get the pass as an input
        user.setPass(password); // set the pass in the object
        System.out.println("Enter your Name:(Default : " + id + ")");
        String name = in.nextLine();// get the name as an input
        if (name.isEmpty()) // if the name is empty
        {
            user.setname(id); // set the id as the name
        }
        else
        {
            user.setname(name); // set the name in the object
        }
        System.out.println("Enter your Location:");
        String location = in.nextLine(); // get the location as an input
        user.setLocation(location); // set the location in the object
        System.out.println("Account Created Successfully");
        BookMyShow_POJO.getUserList().add(user); // add the user to the arrayList

    }
    // method used for user login
    User_POJO login(Scanner in, String id)
    {
        for (User_POJO user : BookMyShow_POJO.getUserList()) // iterate through the user objects
        {
            if (user.getId().equals(id)) // if the id matches
            {
                System.out.println("Enter the password:");
                String password = in.nextLine(); // get the password as input
                if (user.getPass().equals(password)) // if the password matches
                {
                    System.out.println("Login successful!");
                    return user; // return the user object
                }
                else // if pass does not match
                {
                    System.out.println("Invalid password. Try again.");
                    return null; // return null
                }
            }
        }

        System.out.println("User does not exist. Creating a new account...");
        return null; // if user does not exist return null
    }

// method to display available movies
static void availableMovies(User_POJO currentUser )
{
    ArrayList<Movie> moviess = new ArrayList<>(); // create a arrayList of Movies
    System.out.println("-------------------------------");

    LocalDate currentDate = LocalDate.now(); // set the current date


    String location = currentUser.getLocation(); //get the current user location and store it in location variable

    System.out.println("Movies Currently available in your Location: " + location);

    for (String movieName : BookMyShow_POJO.getMovieNameObj().keySet()) // for loop to go through the theatre object
    {
        ArrayList<Movie> movies = BookMyShow_POJO.getMovieNameObj().get(movieName); // get the object of the movie in arraylist

        for (Movie movie : movies) // for loop to go through the arrayList
        {

            if (movie.getLocation().equalsIgnoreCase(location)) // if the location matches with the location of the current user
            {
                LocalDate movieDate = movie.getStartDate(); // get the date of the movie

                if (currentDate.equals(movieDate)) // if the date matches
                {
                    System.out.println(" - " + movie.getMovieName()); // display the movie name
                }
            }
        }
    }
    System.out.println("-------------------------------");

    System.out.println("Would you like to  change the (Date or Location) (Y/N) :"); // prompt the user to change date or location
    String choice = in.nextLine(); // get the choice as input
    if (choice.equals("Y"))
    {
        LocalDate updatedDate = changeLocationOrDate(currentUser); // call the changeLocationOrDate method and pass currentUser
        if (!(updatedDate == null)) // if the returned value is not null then it is a new date
        {
            currentDate = updatedDate; // change the current date to the updated date
        }

        for (String movieName : BookMyShow_POJO.getMovieNameObj().keySet())// for loop to go through the theatre object
        {
            ArrayList<Movie> movies = BookMyShow_POJO.getMovieNameObj().get(movieName);// get the object of the movie in arraylist

            for (Movie movie : movies)// for loop to go through the arrayList
            {

                if (movie.getLocation().equalsIgnoreCase(location))// if the location matches with the location of the current user
                {
                    LocalDate movieDate = movie.getStartDate();// get the date of the movie

                    if (currentDate.equals(movieDate))// if the date matches
                    {
                        System.out.println(" - " + movie.getMovieName());// display the movie name
                    }
                }
            }
        }

    }
    System.out.println("Enter the Movie name to book : ");
    String currentMovie = in.nextLine(); // get the name of the movie to book
    for(var movieObject : BookMyShow_POJO.getMovieNameObj().get(currentMovie))//get the object of the selected movie
    {
        if(movieObject.getLocation().equals(currentUser.getLocation()) && movieObject.getStartDate().isEqual(currentDate)) // if the location and date matches
        {
            moviess.add(movieObject); // add the object to the arrayList
        }
    }
    bookTicket(currentUser , moviess); // call the bookTicket method and pass the currentUser and the movie arrayList
}

// method to change the locationOrDate
static LocalDate changeLocationOrDate(User_POJO currentUser)
{
    // Ask the user if they want to change the location or date
    System.out.println("Do you want to change:");
    System.out.println("1. Location");
    System.out.println("2. Date");
    System.out.println("3. Exit");
    System.out.print("Enter your choice (1 or 2 or 3): ");
    int choice = Integer.parseInt(in.nextLine()); // get the choice as an input

    if (choice == 1) // location
    {

    System.out.println("Your Current Location: " + currentUser.getLocation()); // print the current location

     System.out.println("Available Locations:");
     var availableLocations = new HashSet<>(); // Hashset to store all the locations without duplicates


    for (Theatre_POJO theatre : BookMyShow_POJO.getTheatreNameObj().values()) // for loop to go through the theatre object
    {
        availableLocations.add(theatre.getTheatreLocation()); // add the location of the theatre to the Hashset
    }

    for (var location : availableLocations) // go through the available locations
    {
        System.out.println(" - " + location); // print the location
    }

    System.out.print("Enter your new location: ");
    String newLocation = in.nextLine(); // get the preferred location as input

    if (availableLocations.contains(newLocation)) // check if the location exists
    {
        currentUser.setLocation(newLocation); // if exist then update the location of the current user
        System.out.println("Location changed successfully to: " + newLocation); // display the new location
    }
    else // if the location does not exist
    {
        System.out.println("Invalid location. Please try again.");
    }

    }
    else if (choice == 2) // Date
    {

        System.out.println("Enter your new preferred date : ");
        LocalDate newDate = LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter()); // get the new date as an input

        System.out.println("Date changed successfully to: " + newDate); // display the new Date
        return newDate; // return the new date

    }
    else if (choice == 3) // exit
    {
        System.out.println("Exiting ............");
        return null ; // return null for exit

    }
    else
    {
        System.out.println("Invalid choice. Please select 1 for location or 2 for date.");
    }
        return null ; // return null
    }

// method to book Ticket
static void bookTicket(User_POJO currentUser , ArrayList<Movie> movies)
{
    System.out.println("Currently Available Theatres");
    HashMap<String,HashSet<Show_POJO>> ShowsAtTheatre = new HashMap<>(); //HashMap with theatre key and Hashset of show object

    for(var movie : movies) // for loop to iterate through the movie arrayList
    {
        if(ShowsAtTheatre.containsKey(movie.getTheatre().getTheatreName())) // if the theatre name exist in Hashset key
        {
            ShowsAtTheatre.get(movie.getTheatre().getTheatreName()).add(movie.getShow()); // add the show to the movie
        }
        else // if not exist
        {
            HashSet<Show_POJO> shows = new HashSet<>(); // create an new HashSet of Show
            shows.add(movie.getShow()); // add the movie show to the HashSet
            ShowsAtTheatre.put(movie.getTheatre().getTheatreName(), shows); // put the theatre name as key and show Hashset as value
        }
    }

    for(String theatreName : ShowsAtTheatre.keySet()) // for loop to iterate through the Hashmap and get the Keys
    {
            System.out.println("Theatre Name : "+theatreName); // print the Key (Theatre name)
            System.out.println("Shows : "+ShowsAtTheatre.get(theatreName).toString()); // call the overridden toString method to print the show time
    }

    while (true) // infinite loop to get Booking details
    {
        System.out.println("Enter the theatre name : ");
        String theatreName = in.nextLine(); // get the theatre name as input
        System.out.println("Enter the show time : ");
        LocalTime showTime = LocalTime.parse(in.nextLine(),BookMyShow_POJO.getTimeFormatter()); // get the showTime as input
        HashSet<Show_POJO> shows = ShowsAtTheatre.get(theatreName); // get the show Hashset of the theatre
        if(shows == null) // if show equals null
        {
            System.out.println("Enter correct theatre..."); // print to enter the valid theatre
        }
        Show_POJO currentShow = null; // initialize the currentShow as null
        for(Show_POJO show : shows) // iterate through the Hashset and get the Screen Object
        {
            if(show.getStartTime().equals(showTime)) // if the show time matches
            {
                currentShow = show; // reassign the current show to the show in the object
                break;
            }
        }
        if(currentShow==null) // if null
        {
            System.out.println("Enter the correct time of show...");
            continue;
        }
        else // if not null
        {
            System.out.println("Screen Name : "+currentShow.getScreen().getScreenName());// display the Screen Name
            System.out.println("No of  Seats : "+currentShow.getScreen().getNoOfSeats()); // display the no of seats
            var seatsAndGrids = currentShow.getSeatingArrangement(); // store the seatingArrangement of the show in a variable
            System.out.println("            ----------------- Seating arrangement ---------------           ");
            for(var seats:seatsAndGrids.entrySet()) // get the key and value in for
            {
                System.out.println(seats.getKey()+" "+seats.getValue()); // print the key and the value
            }
        }
        System.out.println("Enter the No Seats to Book");
        long seatCount = Long.parseLong(in.nextLine()); // get the no of seats to book as input
        long price = seatCount*currentShow.getPrice(); // price is seat count * price of a single ticket
        var bookedTickets = seatSelection(seatCount , currentShow);
        if (bookedTickets == null)
        {
            System.out.println("Booking Cancelled ");
            System.out.println("Exiting ....");
            return;
        }
        System.out.println("Paying rupees " + price); // display the total price
        Tickets ticket = new Tickets(theatreName ,movies.getFirst().getMovieName(), currentShow.getScreen().getScreenName() , currentUser.getLocation() , showTime , bookedTickets , price); // pass all the details to the new Ticket object

        currentUser.getTicket().add(ticket); // add the ticket object to the current user ticket arrayList
        break; // break the while loop
    }
}

// method to displayTicket
static void viewTicket(User_POJO currentUser)
{
    ArrayList<Tickets> ticket = currentUser.getTicket();//get the ticket object for the current user
    for (Tickets tickets : ticket) // go through the arrayList
    {
        System.out.println("------------------------------");
        System.out.println("Theatre Name : " + tickets.getTheatreName()); // print the theatre name
        System.out.println("Theatre Location : " + tickets.getLocation()); // print the theatre location
        System.out.println("Movie Name : " + tickets.getMovieName()); // print the movie name
        System.out.println("Screen Name : " + tickets.getScreenName());// print the screen name
        System.out.println("Show Time : " + tickets.getStartTime());// print the show time
        System.out.println("Booked Seats : " + tickets.getSeats()); // print the booked seats
        System.out.println("Price : " + tickets.getPrice());// print the total price
        System.out.println("------------------------------");

    }
}
    static ArrayList<String> seatSelection(long seatCount, Show_POJO currentShow) {
        var seatsAndGrids = currentShow.getSeatingArrangement(); // Store the seating arrangement of the show
        HashMap<Character, ArrayList<String>> duplicateGrid = new HashMap<>(); // HashMap to copy the seating arrangement

        // Deep copy the seating arrangement
        for (var entry : seatsAndGrids.entrySet()) {
            char key = entry.getKey(); // Store the key
            ArrayList<String> value = entry.getValue(); // Store the value as an ArrayList of strings
            ArrayList<String> clonedList = new ArrayList<>(value); // Deep copy the ArrayList
            duplicateGrid.put(key, clonedList); // Put the key and the cloned list into the duplicate grid
        }

        ArrayList<String> bookedTickets = new ArrayList<>(); // ArrayList to store booked tickets

        while (seatCount > 0) { // Loop until all seats are booked
            System.out.println("Enter the Seat Number to book");
            String seat = in.nextLine(); // Get the seat number as input

            char row = seat.charAt(0); // Extract the row from the seat input
            int seatNumber = Integer.parseInt(seat.substring(1)); // Extract the seat number (index-matching)

            String[] grid = currentShow.getScreen().getGrid().split("\\*"); // Parse the grid pattern
            int sum = 0;

            for (String grids : grid) {
                sum += Integer.parseInt(grids); // Calculate the sum of the grid
            }

            String selectedSeat;
            int index;

            if (seatNumber <= Integer.parseInt(grid[0])) { // Seat number <= first grid
                index = seatNumber - 1;
            } else if (seatNumber >= (sum + 1) - Integer.parseInt(grid[2])) { // Seat number >= middle space
                index = seatNumber + 1;
            } else { // Seat number is in between
                index = seatNumber;
            }

            // Check if the seat is already booked
            selectedSeat = currentShow.getSeatingArrangement().get(row).get(index);
            if (selectedSeat.equals("X")) {
                System.out.println("------------------The Selected seat has been already booked-------------------");
                continue; // Skip to the next iteration
            }

            // Mark the seat as booked in the duplicate grid
            duplicateGrid.get(row).set(index, "X");
            bookedTickets.add(seat); // Add the booked seat to the list

            // Display the updated seating arrangement
            for (var seatEntry : duplicateGrid.entrySet()) {
                System.out.println(seatEntry.getKey() + " " + seatEntry.getValue());
            }

            seatCount--; // Decrement the seat count
        }

        // Confirmation before finalizing the booking
        System.out.println("Are you sure you want to book the tickets (Y/N)?");
        String choice = in.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            // Finalize the booking by updating the original seating arrangement
            for (var entry : duplicateGrid.entrySet()) {
                char rowKey = entry.getKey();
                ArrayList<String> updatedRow = entry.getValue();
                currentShow.getSeatingArrangement().put(rowKey, updatedRow);
            }
            System.out.println("Tickets have been successfully booked.");
        } else {
            System.out.println("Booking has been canceled.");
            return null;
        }

        return bookedTickets; // Return the list of booked tickets
    }


}


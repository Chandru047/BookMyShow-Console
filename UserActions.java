//import java.time.LocalDate;
//import java.util.Scanner;
//
//public class UserActions
//{
//    static Scanner in = new Scanner(System.in);
//    static void register(Scanner in, String id)
//    {
//        User_POJO user = new User_POJO();
//        user.setId(id);
//        System.out.println("Enter the Password:");
//        String password = in.nextLine();
//        user.setPass(password);
//        System.out.println("Enter your Name:(Default : " + id + ")");
//        String name = in.nextLine();
//        if (name.isEmpty())
//        {
//            user.setname(id);
//        }
//        else
//        {
//            user.setname(name);
//        }
//        System.out.println("Enter your Location:");
//        String location = in.nextLine();
//        user.setLocation(location);
//        System.out.println("Account Created Successfully");
//        BookMyShow_POJO.getUserList().add(user);
//
//    }
//
//    User_POJO login(Scanner in, String id)
//    {
//        for (int i = 0; i < BookMyShow_POJO.getUserList().size(); i++)
//        {
//            if (BookMyShow_POJO.getUserList().get(i).getId().equals(id))
//            {
//                System.out.println("Enter the password:");
//                String password = in.nextLine();
//                if (BookMyShow_POJO.getUserList().get(i).getPass().equals(password))
//                {
//                    System.out.println("Login successful!");
//                    return BookMyShow_POJO.getUserList().get(i);
//                }
//                else
//                {
//                    System.out.println("Invalid password. Try again.");
//                    return null;
//                }
//            }
//        }
//
//        System.out.println("User does not exist. Creating a new account...");
//        return null;
//    }
//
//    static void availableMovies(User_POJO currentUser) {
//        LocalDate currentDate = LocalDate.now();
//        System.out.println("-------------------------------");
//        while (true) {
//            String location = "";
//            LocalDate start = null;
//            LocalDate end = null;
//            System.out.println("Movies Currently available in your Location");
//
//            location = currentUser.getLocation();
//
//            for (Movie movie : BookMyShow_POJO.getMovie()) {
//                if (movie.getLocation().equals(location)) {
//                    start = movie.getStartDate();
//                    end = movie.getEndDate();
//
//                    if (currentDate.isAfter(start) || currentDate.equals(start) && currentDate.isBefore(end) || currentDate.equals(end)) {
//                        System.out.println(" - " + movie.getMovieName());
//                    }
//                }
//
//            }
//            System.out.println("-------------------------------");
//            break;
//        }
//    }
//    static void displayMovie(User_POJO currentUser)
//    {
//        LocalDate currentDate = LocalDate.now();
//        while (true)
//        {
//            String location = "" ;
//            LocalDate start = null;
//            LocalDate end = null ;
//            System.out.println("Movies Currently available in your Location");
//
//            location =currentUser.getLocation();
//
//            for(Movie movie :BookMyShow_POJO.getMovie())
//            {
//                if (movie.getLocation().equals(location))
//                {
//                    start = movie.getStartDate();
//                    end = movie.getEndDate();
//
//                    if (currentDate.isAfter(start) || currentDate.equals(start) && currentDate.isBefore(end) || currentDate.equals(end))
//                    {
//                        System.out.println(movie.getMovieName());
//                    }
//                }
//
//            }
//
//
//            System.out.println("Do you wish to change the Date(Yes/No)");
//            String choice = in.nextLine();
//            if (choice.equalsIgnoreCase("yes"))
//            {
//                currentDate = changeDate();
//
//            }
//            else
//            {
//                System.out.println("Exiting ...");
//                break;
//            }
//        }
//
//    }
//
//    static void changeLocation(User_POJO currentUser) // method to change the location of the user
//    {
//        System.out.println("Your Current Location :" + currentUser.getLocation());
//
//        System.out.println("Available Locations ... ");
//        for(Movie movie :BookMyShow_POJO.getMovie()) // for loop to display all the available location
//        {
//            System.out.println(" - " + movie.getLocation());
//        }
//
//        System.out.println("Enter your Choice:");
//        String newLocation = in.nextLine();
//
//
//        currentUser.setLocation(newLocation); // setting the location of the curent user
//        System.out.println("Location Changed Successfully ");
//
//
//    }
//
//    static LocalDate changeDate() // method to change the date
//    {
//        System.out.println("Enter the Preferred Date :");
//        LocalDate updatedDate = java.time.LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter());
//        return updatedDate;
//    }
//}

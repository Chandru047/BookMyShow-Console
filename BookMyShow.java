import java.util.Scanner;

public class BookMyShow
{
    static Scanner in = new Scanner(System.in);
    static void start() // start method to start the Application
    {
        System.out.println("----------------------------------------");
        System.out.println("-        Welcome to BookMyShow         -");
        System.out.println("----------------------------------------");


        while (true) // infinite loop for login process
        {
            System.out.println("Enter the Username: ");
            String input = in.nextLine(); // get the userName as input
            if (input.contains("ad")) // if the username contains ad then it is an admin
            {
                AdminActions adminActions = new AdminActions(); // create an object for AdminActions
                Admin_POJO admin = adminActions.login(in, input); // call the login method and store the returned object in admin

                if (admin != null) // if the returned object is not null
                {
                    adminOptions(); // call admin options
                }
                else // if it is null
                {
                    System.out.println("Login or account creation unsuccessful.");
                }
            }

            else if (input.equals("exit")) // if the input is exit
            {
                System.out.println("Exiting.........");
                break; // break the while loop
            }
            else // if it doesn't make to any above conditions then it is a customer
            {
                UserActions userActions = new UserActions(); // create an object for UserActions
                User_POJO user = userActions.login(in, input); // call the login method and store the returned object in user

                if (user != null)// if the returned object is not null
                {
                    userOptions(user);// call user options
                }
                else
                {
                    UserActions.register(in, input); // else take to the register part
                }
            }

        }


    }
   static void adminOptions() // AdminOptions
   {
       loop : while (true) // infinite loop to get the admin operation
       {

           System.out.println("Enter your choice: \n1.Add Movie \n2.View all Movie \n3.Add Theatre \n4.View all Theatres \n5. Exit");
           String choice = in.nextLine(); // get the choice as an input
           switch (choice) // pass the input to the switch
           {
               case "1":
                   AdminActions.addMovie(); // call addMovie method
                   break;
               case "2":
                   AdminActions.viewMovies();// call viewMovie method
                   break;
               case "3":
                   AdminActions.addTheatre();// call addTheatre method
                   break;
               case "4":
                   AdminActions.viewTheatres();// call viewTheatre method
                   break;
               case "5":
                   break loop; // break the while loop

           }
       }

   }

    static void userOptions(User_POJO user) //UserOptions
    {
        UserActions.availableMovies(user); // call the availableMovies method
        loop :while (true) // infinite loop to get the user operations
        {
            System.out.println("Enter your choice: \n1.Display Movie \n2.Change Location or Date \n3.view Ticket \n4.Exit");
            String choice = in.nextLine();// get the choice as an input
            switch (choice)// pass the input to the switch
            {
                case "1":
                    UserActions.availableMovies(user); // call the availableMovies method
                    break;
                case "2":
                    UserActions.changeLocationOrDate(user); // call the changeLocationOrDate method
                    break;
                case "3":
                    UserActions.viewTicket(user); // call the viewTicket method
                    break;
                case "4":
                    break loop; // break the for loop

            }
        }

    }
}

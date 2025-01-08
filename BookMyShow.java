import java.util.Scanner;

public class BookMyShow
{
    static Scanner in = new Scanner(System.in);
    void start()
    {
        System.out.println("----------------------------------------");
        System.out.println("-        Welcome to BookMyShow         -");
        System.out.println("----------------------------------------");


        while (true)
        {
            System.out.println("Enter the Username: ");
            String input = in.nextLine();
            if (input.contains("ad")) {
                AdminActions adminActions = new AdminActions();
                Admin_POJO admin = adminActions.login(in, input);

                if (admin != null)
                {
                    adminOptions();
                }
                else
                {
                    System.out.println("Login or account creation unsuccessful.");
                }
            }

            else if (input.equals("exit"))
            {
                System.out.println("Exiting.........");
                break;
            }
//            else
//            {
//                UserActions userActions = new UserActions();
//                User_POJO user = userActions.login(in, input);
//
//                if (user != null)
//                {
//                    userOptions(user);
//                }
//                else
//                {
//                    UserActions.register(in, input);
//                }
//            }

        }


    }
   static void adminOptions()
   {
       loop : while (true)
       {

           System.out.println("Enter your choice: \n1.Add Movie \n2.View all Movie \n3.Add Theatre \n4.View all Theatres \n5. Exit");
           String choice = in.nextLine().trim();
           switch (choice)
           {
               case "1":
                   AdminActions.addMovie();
                   break;
               case "2":
                   AdminActions.viewMovies();
                   break;
               case "3":
                   AdminActions.addTheatre();
                   break;
               case "4":
                   AdminActions.viewTheatres();
                   break;
               case "5":
                   break loop;

           }
       }

   }

//    static void userOptions(User_POJO user)
//    {
//        loop :while (true)
//        {
//            UserActions.availableMovies(user);
//            System.out.println("Enter your choice: \n1.Display Movie \n2.Change Location \n3.Exit");
//            String choice = in.nextLine().trim();
//            switch (choice)
//            {
//                case "1":
//                    UserActions.displayMovie(user);
//                    break;
//                case "2":
//                    UserActions.changeLocation(user);
//                    break;
//                case "3":
//                    break loop;
//
//            }
//        }
//
//    }
}

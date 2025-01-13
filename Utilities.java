import java.util.ArrayList;
import java.util.HashMap;

public class Utilities
{
    // helper method to generate seating pattern
    static HashMap<Character, ArrayList<String>> generateSeatingPatterns(long numberSeats, String screenGrid)
    {
        long seatCount = numberSeats; // store the no of seats
        String[] screenGridSplit = screenGrid.split("\\*"); // use regex to get the separate values and store it to Array of String
        int sum = 0; // to store the sum of the grid

        for (String grid : screenGridSplit)  // go through the Array of Grid
        {
            long temp = Long.parseLong(grid); // parse the string as long and store it in temp
            sum += (int) temp; // reassign the sum value with type cast temp value
        }

        if (seatCount % sum == 0) // if the grid matches seat count
        {
            var seatArrangment = new HashMap<Character, ArrayList<String>>(); // hashmap to store all the seat arrangment
            char charA = 'A'; // char to describe each row
            while (seatCount > 0) // if seats still left
            {
                ArrayList<String> row = new ArrayList<>(); // arrayList to store the seat and space
                for (int i = 0; i < screenGridSplit.length; i++) // loop to iterate the grid times (5*5*5) --> 3 times
                {
                    for (int j = 0; j < Long.parseLong(screenGridSplit[i]); j++) // loop through the elements
                    {
                        row.add(" _ "); // _ represents seats add it as string
                    }
                    // Add space between seat groups
                    if (i < screenGrid.length() - 1) // spaces after each column
                    {
                        row.add("<SPACE>"); // add space
                    }
                }

                // Add each row to the seating arrangement
                seatArrangment.put(charA, row); // put the key and String ArrayList in Hashmap
                charA++; // increment the character
                seatCount -= sum; // subtract the no of seat with added seats
            }

            return seatArrangment; // return the hashmap
        }

        System.out.println("It is not a valid Grid Layout");
        return null; // if the number of seats and grid does not match then return null
    }
}
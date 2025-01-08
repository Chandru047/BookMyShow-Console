import java.util.ArrayList;
import java.util.HashMap;

public class Utilities
{
    static HashMap<Character, ArrayList<String>> generateSeatingPatterns(long numberSeats , String screenGrid)
    {
        long seatCount = numberSeats;
        String[] screenGridSplit  = screenGrid.split("\\*");
        int sum = 0;

        for (String grid : screenGridSplit)
        {
            long temp = Long.parseLong(grid);
            sum += temp ;
        }

        if (seatCount % sum == 0)
        {
            var seatArrangment = new HashMap<Character , ArrayList<String>>();
            char charA = 'A' ;

            while (seatCount > 0)
            {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 0 ; i< screenGridSplit.length ; i ++)
                {
                    for (int j = 0 ; j < Long.parseLong(screenGridSplit[i]) ; j++)
                    {
                        row.add(" _ ");
                    }

                    if (i < screenGrid.length() -1)
                    {
                        row.add("<SPACE>");
                    }
                }
                seatArrangment.put(charA , row);
                charA ++ ;
                seatCount -= sum;

            }
            return seatArrangment ;
        }

        System.out.println("It is not a valid Grid Layout1");
        return null;
    }
}

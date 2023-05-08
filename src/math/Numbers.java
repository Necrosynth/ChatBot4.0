package math;

import java.util.ArrayList;
import java.util.List;

public class Numbers {
    //NOTE: Integer types max possible value is 2147483647 (Regarding Max Num input for Generation Function)

    private static final int SMALLEST_PRIME_NUMBER = 2;

    // Calculates every Prime Number from 2 up to a given Max Value (inclusive)
    public static List<Integer> generatePrimeNums(int maxNum) { //(Gets all Primes from 2 to maxNum)
        /////////////// SHOULD SOME EXCEPTION HANDLING BE HERE???
        /////

        //List containing all Prime Numbers to be found
        List<Integer> listOfPrimeNums = new ArrayList<>();

        //Check for every prime in a range from 2 (Smallest Prime) to Max Num (inclusive, Largest possible Prime Value to check)
        for (int currentNum = SMALLEST_PRIME_NUMBER; currentNum <= maxNum; currentNum++) {
            //Check all Potential Factors of Current Number. Range of values checked: Min: 2, Max: Half of the value of Current Number
            // ( Ranges Max is: Max value possible for a potentially valid factor )
            for (int potentialFactor = SMALLEST_PRIME_NUMBER; potentialFactor <= currentNum / SMALLEST_PRIME_NUMBER; potentialFactor++) {
                //If a smallest valid factor found, Current Number can't be prime
                if (currentNum % potentialFactor == 0 && currentNum != SMALLEST_PRIME_NUMBER) {
                    break; //Stop check for more potential factors, continue checking for the next prime in range

                    //Check a range for all potentially valid factors.
                } else if (currentNum == SMALLEST_PRIME_NUMBER || potentialFactor == currentNum / SMALLEST_PRIME_NUMBER) {
                    listOfPrimeNums.add(currentNum); //If no valid factors are found in the range, add prime to list
                }
            }
        }
        return listOfPrimeNums;
    }
}
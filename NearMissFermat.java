/* Title: Near Miss Finder for Fermat's Equation
 * NearMissFermat.java
 *
 * External Files needed to run: N/A
 * External Files created: N/A
 * 
 * Programmers: Daniel Hernandez, Dalayno Franklin
 * Email: danielhernandez1@lewisu.edu, dalaynofranklin@lewisu.edu
 * SP25-CPSC-44000-LT1
 *
 * Date finished: 2/16/2025
 *
 * Explanation:
 * This program searches for "near misses" in Fermat's equation: x^n + y^n ≠ z^n
 * It calculated x^n + y^n for x and y in the range [10, k] and finds the integer z
 * such that z^n or (z+1)^n is closest to the sum. When a new smallest relative miss is found
 * it prints the current best values.
 *
 * Resources
 * 
 * 
 */

import java.io.IOException; // For handling exceptions
import java.util.Scanner; // For reading user input

public class NearMissFermat {
    /**
     *
     * Allows the program to run by calling helper methods
     */
    public static void main(String[] args) {
        // Create a scanner object
        Scanner scanner = new Scanner(System.in);

        // Get n exponent
        int n = getExponent(scanner);
        // Get upper bound k
        int k = getUpperBound(scanner);
        algorithm(n, k);

        // Pauses before exiting program
        System.out.println("Press any key to exit...");
        try {
            System.in.read(); // Wait for any key press, including Enter
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace if exception occurs
        }

    }

    /**
     * Prompts user to enter an exponent where 2 < n < 12
     * @param scanner to read user input
     * @return valid exponent (n)
     */
    public static int getExponent(Scanner scanner) {
        int n;
        // Loop until a valid integer in the range is entered
        while (true) {
            System.out.print("Enter the exponent n (must be an integer with 2 < n < 12): ");
            if (scanner.hasNextInt()) { // check if input is integer
                n = scanner.nextInt();
                if (n > 2 && n < 12) { // check if integer is between 2 and 12
                    break; // stop loop if valid input is received
                } else {
                    System.out.println("Error: n must be greater than 2 and less than 12"); // error our if integer not in range
                }
            } else {
                System.out.println("Error: Please enter a valid integer for n"); // error if input is not an integer
                scanner.next(); // clear invalid input
            }
        }
        return n; // return integer if valid
    }

    /**
     * This gets the upper bound where k >= 10
     * @param scanner to read user input
     * @return a valid upper bound (k)
     */
    public static int getUpperBound(Scanner scanner) {
        int k; // Declaration for k
        // loop until valid integer for k is entered
        while (true) {
            System.out.print("Enter the upper bound k (must be an integer >= 10): ");
            if (scanner.hasNextInt()) { // check if input is an integer
                k = scanner.nextInt();
                if (k >= 10) { // check if k is greater than or equal to 10
                    break; // stop loop if valid input is received
                } else {
                    System.out.println("Error: k must be at least 10"); // error if k is less than 10
                }
            } else {
                System.out.println("Error: Please enter a valid integer for k."); // error is k is not an integer
                scanner.next(); // clear invalid input
            }
        }
        return k; // return k if it is valid
    }

    /* Runs the algorithm to find near misses on x^n+y^n=z^n.
     * @param n Integer for the power
     * @param k Integer for the upperbound limit
     * 
    */
    public static void algorithm(int n, int k){
        int newX = 0; // Takes the x variable from the smallest miss
        int newY = 0; // Takes the y variable from the smallest miss
        int newZ = 0; // Takes the z variable from the smallest miss
        int z = 0; // Initalizes z for the equation
        double smallestRM = 999999999; // Variable that will contain the smallest Relative Miss

        /* For loop that will intialize the beginning of the equation x^n+y^n=z^n. 
         * X is set to 10 as a requiement of the assignment.
         * Cycles through until x > k. 
         */
        for (int x = 10; x <= k; x++) {
            // For loop that sets y = 10 and cycles until y > k
            for (int y = 10; y <= k; y++) {
                long sum = (long) Math.pow(x, n) + (long) Math.pow(y, n); // Adds x^n and y^n into the variable sum
                z = (int) Math.pow(sum, 1.0 / n); // Finds the nth square root of the sum and sets it to z as a whole number
                
                long z1 = (long) Math.pow(z, n); // Finds the sum of z to the nth power and sets it to z1
                long z2 = (long) Math.pow(z + 1, n); // Finds the sum of z+1 to the nth power and sets it to z2              
                
                long miss1 = sum - z1; // Subtracts z1 from the sum
                long miss2 = z2 - sum; // Subtracts the sum from z2                
                long miss = Math.min(miss1, miss2); // miss = the smaller variable

                // Checks if miss is equal to miss2
                if(miss == miss2)
                    z = z + 1;
                
                double relativeMiss = (double) miss / sum; // miss is divided by sum with the new sum being a double variable for precision
                
                //Print for debugging
                // System.out.printf("Current x: %d y: %d z: %d Sum: %d Miss: %d RM: %.5f%%\n", 
                //                 x,y,z,sum,miss,relativeMiss);
                
                // Checks if relativeMiss is less than smallestRM
                if (relativeMiss < smallestRM) { 
                    newX = x;
                    newY = y;
                    newZ = z;  
                    smallestRM = relativeMiss;
                    
                    System.out.printf("Current smallest results x: %d y: %d z: %d Miss: %d Relative miss: %.15f%%\n", 
                                      newX, newY, newZ, miss, smallestRM * 100);
                }
            }
        }
    }

}

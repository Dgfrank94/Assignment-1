/* Title
 * NearMissFermat.java
 * 
 * 
 * Daniel Hernandez
 * danielhernandez1@lewisu.edu
 * SP25-CPSC-44000-LT1
 * (Date finshed)
 * (Explaantion of program)
 * etc
 * 
 * 
 * 
 * 
 * 
 */

import java.util.Scanner;

public class NearMissFermat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get n exponent
        int n = getExponent(scanner);
        int k = getUpperBound(scanner);
        algorithm(n, k);

    }

    /**
     * Prompts user to enter an exponent
     * @param scanner to read user input
     * @return valid exponent
     */
    public static int getExponent(Scanner scanner) {
        int n;
        while (true) {
            System.out.print("Enter the exponent n (must be an integer with 2 < n < 12): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 2 && n < 12) {
                    break;
                } else {
                    System.out.println("Error: n must be greater than 2 and less than 12");
                }
            } else {
                System.out.println("Error: Please enter a valid integer for n");
                scanner.next();
            }
        }
        return n;
    }

    public static int getUpperBound(Scanner scanner) {
        int k;
        while (true) {
            System.out.print("Enter the upper bound k (must be an integer >= 10): ");
            if (scanner.hasNextInt()) {
                k = scanner.nextInt();
                if (k >= 10) {
                    break;
                } else {
                    System.out.println("Error: k must be at least 10");
                }
            } else {
                System.out.println("Error: Please enter a valid integer for k.");
                scanner.next();
            }
        }
        return k;
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
                int sum = (int) Math.pow(x, n) + (int) Math.pow(y, n); // Adds x^n and y^n into the variable sum
                z = (int) Math.pow(sum, 1.0 / n); // Finds the nth square root of the sum and sets it to z as a whole number
                
                int z1 = (int) Math.pow(z, n); // Finds the sum of z to the nth power and sets it to z1
                int z2 = (int) Math.pow(z + 1, n); // Finds the sum of z+1 to the nth power and sets it to z2              
                
                int miss1 = sum - z1; // Subtracts z1 from the sum
                int miss2 = z2 - sum; // Subtracts the sum from z2                
                int miss = Math.min(miss1, miss2); // miss = the smaller variable

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

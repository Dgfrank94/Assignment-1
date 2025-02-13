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

    public static void algorithm(int n, int k){
        int newX = 0;
        int newY = 0;
        int newZ = 0;
        int z = 0;
        double smallestRM = 999999999;
        for (int x = 10; x <= k; x++) {
            for (int y = 10; y <= k; y++) {
                int sum = (int) Math.pow(x, n) + (int) Math.pow(y, n);
                z = (int) Math.pow(sum, 1.0 / n);
                
                int z1 = (int) Math.pow(z, n);
                int z2 = (int) Math.pow(z + 1, n);                
                
                int miss1 = sum - z1;
                int miss2 = z2 - sum;                
                int miss = Math.min(miss1, miss2);
                
                //Print for debugging
                //System.out.printf("z: %d z^n: %d\n", z,(int)Math.pow(z, n));
                //System.out.printf("z+1: %d z^n: %d\n", (z+1),(int)Math.pow((z+1), n));

                if(miss == miss2)
                    z = z + 1;
                
                double relativeMiss = (double) miss / sum;
                
                //Print for debugging
                System.out.printf("Current x: %d y: %d z: %d Sum: %d Miss: %d RM: %.5f%%\n", 
                                x,y,z,sum,miss,relativeMiss);
                
                if (relativeMiss < smallestRM) { 
                    newX = x;
                    newY = y;
                    newZ = z;  
                    smallestRM = relativeMiss;                                   
                    System.out.printf("Smallest results- x: %d y: %d z: %d Sum: %d Relative miss: %.10f%%\n\n", 
                                      newX, newY, newZ, sum, smallestRM * 100);
                }
            }
        }
    }

}

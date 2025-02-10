import java.util.Scanner;

public class NearMissFermat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get n exponent
        int n = getExponent(scanner);
        int k = getUpperBound(scanner);

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

}

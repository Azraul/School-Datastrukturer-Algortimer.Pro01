package travel_app;

import java.util.Scanner;

public class ValidateScanner {
    /************************************************************
     *  *** Validation functions used to avoid scanner errors ***
     *  ***         variable = validateScanner(variable)      ***
     *  *********************************************************/
    public static class validateScanner {
        public static int validateScanner() {
            Scanner sc = new Scanner(System.in);
            int number;
                while (!sc.hasNextInt()) {
                    System.out.println("Vänligen ange ett heltal");
                    sc.next();
                }
                number = sc.nextInt();
            return number;
        }
    }
}

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        // Test 1: Create a default polynomial and evaluate at x = 3
        Polynomial p = new Polynomial();
        System.out.println("Default polynomial evaluated at 3: " + p.evaluate(3));

        // Test 2: Create first polynomial with coefficients and exponents
        double[] c1 = {6, 0, 0, 5};  // Coefficients
        int[] e1 = {3, 2, 1, 0};     // Exponents for the polynomial 6x^3 + 5
        Polynomial p1 = new Polynomial(c1, e1);
        System.out.println("Polynomial p1: " + p1);

        // Test 3: Create second polynomial with coefficients and exponents
        double[] c2 = {0, -2, 0, 0, -9};  // Coefficients
        int[] e2 = {4, 3, 2, 1, 0};      // Exponents for the polynomial -2x^3 - 9
        Polynomial p2 = new Polynomial(c2, e2);
        System.out.println("Polynomial p2: " + p2);

        // Test 4: Add polynomials p1 and p2
        Polynomial sum = p1.add(p2);
        System.out.println("Sum of p1 and p2: " + sum);

        // Test 5: Evaluate the sum polynomial at x = 0.1
        System.out.println("Sum evaluated at 0.1: " + sum.evaluate(0.1));

        // Test 6: Check if 1 is a root of the sum polynomial
        if (sum.hasRoot(1)) {
            System.out.println("1 is a root of the sum polynomial.");
        } else {
            System.out.println("1 is not a root of the sum polynomial.");
        }

        // Test 7: Multiply polynomials p1 and p2
        Polynomial product = p1.multiply(p2);
        System.out.println("Product of p1 and p2: " + product);

        // Test 8: Save the result (sum) to a file
        sum.saveToFile("output.txt");

        // FILE TESTING
        try {
            // Test 9: Create a polynomial by reading from a file (polynomial.txt)
            File file = new File("poly.txt");
            Polynomial polyFromFile = new Polynomial(file);

            // Test 10: Print the polynomial read from the file
            System.out.println("Polynomial from file: " + polyFromFile);

            // Test 11: Evaluate the polynomial from the file at x = 1
            System.out.println("Polynomial from file evaluated at x = 1: " + polyFromFile.evaluate(1));

            // Test 12: Check if 0 is a root of the polynomial from the file
            if (polyFromFile.hasRoot(0)) {
                System.out.println("0 is a root of the polynomial from the file.");
            } else {
                System.out.println("0 is not a root of the polynomial from the file.");
            }

            // Test 13: Save the polynomial read from the file to a new file (output_polynomial.txt)
            polyFromFile.saveToFile("output_polynomial.txt");
            System.out.println("Polynomial from file saved to output_polynomial.txt");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
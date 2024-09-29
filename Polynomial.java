import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class Polynomial{

    private double[] coefficients;
    private int[] exponents;

    public Polynomial() {
        coefficients = new double[]{0};
        exponents = new int[]{0};
    }

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients =  coefficients;
        this.exponents = exponents;
    }

    public Polynomial add(Polynomial poly) {
        int maxSize = this.coefficients.length + poly.coefficients.length;
        double[] coeffres = new double[maxSize];
        int[] expres = new int[maxSize];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < this.coefficients.length && j < poly.coefficients.length) {
            if (this.exponents[i] == poly.exponents[j]) {
                coeffres[k] = this.coefficients[i] + poly.coefficients[j];
                expres[k] = this.exponents[i];
                i++; 
                j++;
                k++; 
            } else if (this.exponents[i] > poly.exponents[j]) {
                coeffres[k] = this.coefficients[i];
                expres[k] = this.exponents[i];
                i++;
                k++;
            } else {
                coeffres[k] = poly.coefficients[j];
                expres[k] = poly.exponents[j];
                j++;
                k++;
            }
        }

        while (i < this.coefficients.length) {
            coeffres[k] = this.coefficients[i];
            expres[k] = this.exponents[i];
            i++;
            k++;
        }

        double[] finalCoeffres = new double[k];
        int[] finalExpres = new int[k];

        for (int index = 0; index < k; index++) {
            finalCoeffres[index] = coeffres[index];
            finalExpres[index] = expres[index];
        }

        return new Polynomial(finalCoeffres, finalExpres);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0; 
    }

    public Polynomial multiply(Polynomial p) {
        Map<Integer, Double> resultMap = new HashMap<>();

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < p.coefficients.length; j++) {
                double newCoeff = this.coefficients[i] * p.coefficients[j];
                int newExp = this.exponents[i] + p.exponents[j];
    
                resultMap.put(newExp, resultMap.getOrDefault(newExp, 0.0) + newCoeff);
            }
        }
        int size = resultMap.size();
        double[] finalCoeffresult = new double[size];
        int[] finalExpresult = new int[size];
        int index = 0;
        for (Map.Entry<Integer, Double> entry : resultMap.entrySet()) {
            finalExpresult[index] = entry.getKey();
            finalCoeffresult[index] = entry.getValue();
            index++;
        }
    
        return new Polynomial(finalCoeffresult, finalExpresult);
    }

    public Polynomial(File file) throws FileNotFoundException {
        try {
            Scanner myReader = new Scanner(file);
            String strPoly = myReader.nextLine();
            myReader.close();
    
            Map<Integer, Double> termMap = new HashMap<>();
    
            String[] terms = strPoly.split("(?=[+-])");
    
            for (String term : terms) {
                term = term.trim();
                int exponent = 0;
                double coefficient = 1;
    
                if (term.contains("x")) {
                    String[] parts = term.split("x");
    
                    if (parts[0].isEmpty() || parts[0].equals("+")) {
                        coefficient = 1;
                    } else if (parts[0].equals("-")) {
                        coefficient = -1;
                    } else {
                        coefficient = Double.parseDouble(parts[0]);
                    }
    
                    if (parts.length > 1 && parts[1].contains("^")) {
                        exponent = Integer.parseInt(parts[1].substring(1));
                    } else {
                        exponent = 1; 
                    }
                } else {
                    coefficient = Double.parseDouble(term);
                }
    
                termMap.put(exponent, termMap.getOrDefault(exponent, 0.0) + coefficient);
            }
    
            int size = termMap.size();
            coefficients = new double[size];
            exponents = new int[size];
            int index = 0;
            for (Map.Entry<Integer, Double> entry : termMap.entrySet()) {
                exponents[index] = entry.getKey();
                coefficients[index] = entry.getValue();
                index++;
            }
    
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
                for (int i = 0; i < coefficients.length; i++) {
                if (i > 0 && coefficients[i] > 0) {
                    writer.write("+");  
                }
                writer.write(coefficients[i] + ""); 
                
                if (exponents[i] != 0) {
                    writer.write("x^" + exponents[i]); 
                }
            }
    
            writer.close();
            System.out.println("Polynomial saved to file: " + filename);
            
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
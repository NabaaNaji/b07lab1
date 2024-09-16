public class Polynomial{

    private double[] coefficients;

    public Polynomial() {
        coefficients = new double[]{0};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients =  coefficients;
    }

    public Polynomial add(Polynomial poly) {
        int maxLength = Math.max(this.coefficients.length, poly.coefficients.length);
        double[] resultCoefficients = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double thisCoeff = i < this.coefficients.length ? this.coefficients[i] : 0;
            double polyCoeff = i < poly.coefficients.length ? poly.coefficients[i] : 0;
            resultCoefficients[i] = thisCoeff + polyCoeff;
        }

        return new Polynomial(resultCoefficients);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0; 
    }

}
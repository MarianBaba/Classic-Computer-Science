package simplest;

public class Pi {

    // we want to calculate Pi with the Leibniz formula:
    // pi = 4/1 + 4/3 - 4/5 + 4/7 - 4/9 + ...

    public static double calculatePi(int terms) {
        final double numerator = 4.0;
        double denominator = 1.0;
        double operation = 1.0;
        double pi = 0.0;

        for (int i = 0; i < terms; i++) {
            pi += operation * (numerator / denominator);
            denominator += 2.0;
            operation = -operation;
        }

        return pi;
    }

    public static void main(String... strings) {
        System.out.println(calculatePi(1000000000));
    }

}

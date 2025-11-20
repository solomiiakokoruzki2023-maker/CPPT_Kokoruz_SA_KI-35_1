package KI35.Kokoruz.Lab5;

public class ExpressionDriver {
    public static void main(String[] args) {
        try {
            ExpressionCalculator calc = new ExpressionCalculator();

            double x = 1.1;
            double y = calc.calculate(x);

            FileManager.writeText("result.txt", x, y);
            FileManager.writeBinary("result.dat", x, y);

            System.out.println("-- Text file read --");
            FileManager.readText("result.txt");

            System.out.println("-- Binary file read --");
            FileManager.readBinary("result.dat");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

package KI35.Kokoruz.Lab4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Клас ExpressionDriver демонструє роботу ExpressionCalculator.
 * Результат обчислення записується у файл Lab4/result.txt
 */
public class ExpressionDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionCalculator calc = new ExpressionCalculator();

        try {
            System.out.print("Введіть значення x (у радіанах): ");
            double x = scanner.nextDouble();

            double result = calc.calculate(x);

            System.out.println("Результат обчислення: y = " + result);

            // Запис у файл (перезаписуємо старий вміст)
            try (FileWriter writer = new FileWriter("Lab4/result.txt", false)) {
                writer.write("x = " + x + System.lineSeparator());
                writer.write("y = " + result + System.lineSeparator());
            }
            System.out.println("Результат записано у файл Lab4/result.txt");

        } catch (ArithmeticException e) {
            System.out.println("Арифметична помилка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

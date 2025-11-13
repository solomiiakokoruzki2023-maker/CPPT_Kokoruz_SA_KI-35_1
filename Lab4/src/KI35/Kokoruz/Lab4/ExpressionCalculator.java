package KI35.Kokoruz.Lab4;

/**
 * Клас ExpressionCalculator реалізує обчислення математичного виразу y = ctg(x) / tg(x).
 *
 * @author Kokoruz
 * @version 1.0
 */
public class ExpressionCalculator {

    /**
     * Метод для обчислення виразу y = ctg(x) / tg(x).
     * @param x значення змінної x у радіанах
     * @return результат виразу y
     * @throws ArithmeticException якщо tg(x) = 0
     */
    public double calculate(double x) throws ArithmeticException {
        double tanX = Math.tan(x);

        // Захист від ділення на нуль (тангенс дуже маленький)
        if (Math.abs(tanX) < 1e-15) {
            throw new ArithmeticException("Помилка: tg(x) = 0 (або дуже близьке до нуля). Ділення на нуль неможливе!");
        }

        double cotX = 1.0 / tanX;
        double y = cotX / tanX; // y = 1 / (tan^2 x)
        return y;
    }
}

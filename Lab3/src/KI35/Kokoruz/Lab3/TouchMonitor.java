package KI35.Kokoruz.Lab3;

import KI35.Kokoruz.Lab2.*; // Імпортуємо базові класи з Lab2

/**
 * TouchMonitor — підклас монітора з сенсорним екраном.
 * Реалізує інтерфейс TouchControl.
 */
public class TouchMonitor extends Monitor implements TouchControl {
    private boolean touchEnabled;
    private int touchPoints; // кількість точок дотику

    /**
     * Конструктор з параметрами.
     */
    public TouchMonitor(String model, Screen screen, Stand stand, Cable cable, int touchPoints) {
        super(model, screen, stand, cable);
        this.touchPoints = touchPoints;
        this.touchEnabled = false;
    }

    /**
     * Вмикає сенсорну панель.
     */
    @Override
    public void enableTouch() {
        if (!touchEnabled) {
            touchEnabled = true;
            System.out.println("Touch control enabled.");
        }
    }

    /**
     * Вимикає сенсорну панель.
     */
    @Override
    public void disableTouch() {
        if (touchEnabled) {
            touchEnabled = false;
            System.out.println("Touch control disabled.");
        }
    }

    /**
     * Виводить інформацію про сенсорний монітор.
     */
    public void showTouchInfo() {
        System.out.println("Touch screen: " + (touchEnabled ? "ON" : "OFF") + ", points: " + touchPoints);
        System.out.println(status());
    }
}

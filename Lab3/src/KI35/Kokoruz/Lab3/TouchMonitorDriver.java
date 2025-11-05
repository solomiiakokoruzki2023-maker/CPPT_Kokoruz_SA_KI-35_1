package KI35.Kokoruz.Lab3;

import KI35.Kokoruz.Lab2.*;

/**
 * Демонстраційний клас для перевірки роботи TouchMonitor.
 */
public class TouchMonitorDriver {
    public static void main(String[] args) {
        Screen screen = new Screen(1920, 1080);
        Stand stand = new Stand("Aluminum stand");
        Cable cable = new Cable("Power");
        cable.connect();

        TouchMonitor tm = new TouchMonitor("Dell-Touch-24", screen, stand, cable, 10);

        tm.connectPowerCable();
        tm.powerOn();
        tm.enableTouch();
        tm.setBrightness(75);
        tm.setContrast(60);
        tm.showTouchInfo();
    }
}

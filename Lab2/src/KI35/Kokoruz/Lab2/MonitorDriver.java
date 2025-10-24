package KI35.Kokoruz.Lab2;

/**
 * Клас-драйвер для тестування Monitor.
 */
public class MonitorDriver {
    public static void main(String[] args) {
        Screen screen = new Screen();
        Stand stand = new Stand();
        Cable cable = new Cable("Power");

        Monitor monitor = new Monitor("Samsung-27", screen, stand, cable);

        monitor.connectPowerCable();
        monitor.powerOn();
        monitor.setBrightness(80);
        monitor.setContrast(70);
        monitor.rotate(90);

        System.out.println(monitor.status());

        monitor.powerOff();
    }
}

package KI35.Kokoruz.Lab2;

/**
 * MonitorDriver — клас-драйвер для демонстрації роботи класу Monitor.
 */
public class MonitorDriver {
    public static void main(String[] args) {
        Monitor m1 = new Monitor("Dell-U2719", new Screen("IPS", 2560, 1440), new Stand(true, 0, 120), new Cable("Power"));
        // Демонстрація роботи:
        m1.connectPowerCable();
        m1.powerOn();
        m1.increaseBrightness(10);
        m1.setContrast(60);
        m1.rotate(90);
        m1.setResolution(1920, 1080);
        m1.saveSettings();
        m1.simulateFirmwareUpdate();
        System.out.println("Status m1: " + m1.status());

        // Другий екземпляр
        Monitor m2 = new Monitor();
        m2.connectPowerCable();
        m2.powerOn();
        m2.decreaseBrightness(20);
        m2.disconnectPowerCable(); // демонстрація
        System.out.println("Status m2: " + m2.status());

        // В кінці викликаємо shutdownLogger() хоча це вже виконається shutdown hook'ом
        Monitor.shutdownLogger();
        System.out.println("Done. Check monitor.log for activity log.");
    }
}

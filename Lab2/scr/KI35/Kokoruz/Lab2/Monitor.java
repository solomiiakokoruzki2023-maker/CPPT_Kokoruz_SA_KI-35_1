package KI35.Kokoruz.Lab2;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Monitor — головний клас предметної області (варіант "монітор").
 *
 * Містить як мінімум 3 поля-об'єкти: Screen, Stand, Cable.
 * Має кілька конструкторів, мінімум 10 методів, веде лог своєї діяльності у файл.
 *
 * Javadoc-опис для автоматичної генерації документації.
 */
public class Monitor {
    private static final String LOG_FILE = "monitor.log";
    private static final Logger logger = Logger.getLogger(Monitor.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            logger.info("Logger initialized.");
            // Додаємо shutdown hook щоб коректно закрити handler
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutdown hook: closing log handlers.");
                shutdownLogger();
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Screen screen;
    private Stand stand;
    private Cable powerCable;

    private boolean powerOn;
    private int brightness; // 0..100
    private int contrast;   // 0..100
    private int rotation;   // 0,90,180,270
    private String model;

    /**
     * Головний конструктор.
     */
    public Monitor(String model, Screen screen, Stand stand, Cable powerCable) {
        this.model = model;
        this.screen = screen;
        this.stand = stand;
        this.powerCable = powerCable;
        this.powerOn = false;
        this.brightness = 50;
        this.contrast = 50;
        this.rotation = 0;
        logger.info("Monitor created: " + model + " screen=" + screen + " stand=" + stand + " cable=" + powerCable);
    }

    /**
     * Простий конструктор за замовчуванням.
     */
    public Monitor() {
        this("GenericModel-1", new Screen(), new Stand(), new Cable("Power"));
    }

    /**
     * Конструктор з моделлю і типовими компонентами.
     */
    public Monitor(String model) {
        this(model, new Screen(), new Stand(), new Cable("Power"));
    }

    // ------- Методи керування (мінімум 10) -------

    public synchronized void powerOn() {
        if (powerOn) {
            logger.info("powerOn(): already on.");
            return;
        }
        if (!powerCable.isConnected()) {
            logger.warning("powerOn(): cannot power on — cable disconnected.");
            return;
        }
        powerOn = true;
        logger.info("powerOn(): Monitor powered on.");
    }

    public synchronized void powerOff() {
        if (!powerOn) {
            logger.info("powerOff(): already off.");
            return;
        }
        powerOn = false;
        logger.info("powerOff(): Monitor powered off.");
    }

    public synchronized void increaseBrightness(int step) {
        setBrightness(brightness + step);
    }

    public synchronized void decreaseBrightness(int step) {
        setBrightness(brightness - step);
    }

    public synchronized void setBrightness(int value) {
        int old = brightness;
        brightness = Math.max(0, Math.min(100, value));
        logger.info("setBrightness(): " + old + " -> " + brightness);
    }

    public synchronized void setContrast(int value) {
        int old = contrast;
        contrast = Math.max(0, Math.min(100, value));
        logger.info("setContrast(): " + old + " -> " + contrast);
    }

    public synchronized void rotate(int degrees) {
        if (degrees % 90 != 0) {
            logger.warning("rotate(): invalid degrees " + degrees);
            return;
        }
        int old = rotation;
        rotation = ((degrees % 360) + 360) % 360;
        logger.info("rotate(): " + old + " -> " + rotation);
    }

    public synchronized void connectPowerCable() {
        powerCable.connect();
        logger.info("connectPowerCable(): " + powerCable);
    }

    public synchronized void disconnectPowerCable() {
        powerCable.disconnect();
        logger.info("disconnectPowerCable(): " + powerCable);
    }

    public synchronized void setResolution(int w, int h) {
        int oldW = screen.getWidth();
        int oldH = screen.getHeight();
        screen.setResolution(w, h);
        logger.info("setResolution(): " + oldW + "x" + oldH + " -> " + w + "x" + h);
    }

    public synchronized String status() {
        String s = String.format("Model=%s, power=%s, brightness=%d, contrast=%d, rotation=%d, screen=%s, stand=%s, cable=%s",
                model, powerOn ? "ON" : "OFF", brightness, contrast, rotation, screen, stand, powerCable);
        logger.info("status(): " + s);
        return s;
    }

    // Додаткові демонстраційні методи
    public synchronized void simulateFirmwareUpdate() {
        logger.info("simulateFirmwareUpdate(): started.");
        try {
            Thread.sleep(300); // імітація часу оновлення
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("simulateFirmwareUpdate(): finished.");
    }

    public synchronized void saveSettings() {
        // імітуємо збереження налаштувань
        logger.info("saveSettings(): brightness=" + brightness + ", contrast=" + contrast + ", rotation=" + rotation);
    }

    public synchronized void loadSettings() {
        // імітуємо завантаження
        logger.info("loadSettings(): settings loaded.");
    }

    // Гетер/сетер для тестів
    public Screen getScreen() { return screen; }
    public Stand getStand() { return stand; }
    public Cable getPowerCable() { return powerCable; }
    public String getModel() { return model; }

    /**
     * Коректно закриває файлові оброблювачі логера.
     */
    public static void shutdownLogger() {
        if (fileHandler != null) {
            fileHandler.flush();
            fileHandler.close();
            logger.removeHandler(fileHandler);
            fileHandler = null;
        }
    }
}

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
            Runtime.getRuntime().addShutdownHook(new Thread(Monitor::shutdownLogger));
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

    public Monitor(String model, Screen screen, Stand stand, Cable powerCable) {
        this.model = model;
        this.screen = screen;
        this.stand = stand;
        this.powerCable = powerCable;
        this.powerOn = false;
        this.brightness = 50;
        this.contrast = 50;
        this.rotation = 0;
        logger.info("Monitor created: " + model);
    }

    public Monitor() {
        this("GenericModel-1", new Screen(), new Stand(), new Cable("Power"));
    }

    public Monitor(String model) {
        this(model, new Screen(), new Stand(), new Cable("Power"));
    }

    public synchronized void powerOn() {
        if (powerOn) return;
        if (!powerCable.isConnected()) return;
        powerOn = true;
        logger.info("Monitor powered on.");
    }

    public synchronized void powerOff() {
        if (!powerOn) return;
        powerOn = false;
        logger.info("Monitor powered off.");
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
        if (degrees % 90 != 0) return;
        int old = rotation;
        rotation = ((degrees % 360) + 360) % 360;
        logger.info("rotate(): " + old + " -> " + rotation);
    }

    public synchronized void connectPowerCable() {
        powerCable.connect();
        logger.info("Cable connected.");
    }

    public synchronized void disconnectPowerCable() {
        powerCable.disconnect();
        logger.info("Cable disconnected.");
    }

    public synchronized void setResolution(int w, int h) {
        int oldW = screen.getWidth();
        int oldH = screen.getHeight();
        screen.setResolution(w, h);
        logger.info("Resolution: " + oldW + "x" + oldH + " -> " + w + "x" + h);
    }

    public synchronized String status() {
        String s = String.format("Model=%s, power=%s, brightness=%d, contrast=%d, rotation=%d, screen=%s, stand=%s, cable=%s",
                model, powerOn ? "ON" : "OFF", brightness, contrast, rotation, screen, stand, powerCable);
        logger.info("Status: " + s);
        return s;
    }

    public static void shutdownLogger() {
        if (fileHandler != null) {
            fileHandler.flush();
            fileHandler.close();
            logger.removeHandler(fileHandler);
            fileHandler = null;
        }
    }
}

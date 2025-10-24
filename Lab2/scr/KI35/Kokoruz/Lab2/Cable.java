package KI35.Kokoruz.Lab2;

/**
 * Cable — простий опис кабелю (наприклад, HDMI, DisplayPort, Power).
 */
public class Cable {
    private String type;
    private boolean connected;

    public Cable(String type) {
        this.type = type;
        this.connected = false;
    }

    public Cable() { this("HDMI"); }

    public String getType() { return type; }
    public boolean isConnected() { return connected; }

    public void connect() { connected = true; }
    public void disconnect() { connected = false; }

    @Override
    public String toString() {
        return type + (connected ? " (connected)" : " (disconnected)");
    }
}

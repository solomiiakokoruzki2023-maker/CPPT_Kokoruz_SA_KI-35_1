package KI35.Kokoruz.Lab2;

/**
 * Клас Cable описує кабель живлення.
 */
public class Cable {
    private String type;
    private boolean connected;

    public Cable(String type) {
        this.type = type;
        this.connected = false;
    }

    public void connect() { connected = true; }
    public void disconnect() { connected = false; }
    public boolean isConnected() { return connected; }

    @Override
    public String toString() { return type + (connected ? " (connected)" : " (disconnected)"); }
}

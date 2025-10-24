package KI35.Kokoruz.Lab2;

/**
 * Screen — опис дисплейної частини монітора.
 */
public class Screen {
    private String panelType; // наприклад IPS, TN
    private int width;        // пікселі
    private int height;       // пікселі

    /**
     * Конструктор Screen.
     * @param panelType тип панелі
     * @param width ширина в пікселях
     * @param height висота в пікселях
     */
    public Screen(String panelType, int width, int height) {
        this.panelType = panelType;
        this.width = width;
        this.height = height;
    }

    public Screen() {
        this("IPS", 1920, 1080);
    }

    public String getPanelType() { return panelType; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return panelType + " " + width + "x" + height;
    }
}

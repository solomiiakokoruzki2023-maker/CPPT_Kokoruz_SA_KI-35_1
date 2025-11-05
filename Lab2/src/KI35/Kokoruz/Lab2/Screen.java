package KI35.Kokoruz.Lab2;

/**
 * Клас Screen описує екран монітора.
 */
public class Screen {
    private int width;
    private int height;

    // ✅ Конструктор без параметрів (вже був)
    public Screen() {
        this.width = 1920;
        this.height = 1080;
    }

    // ✅ Новий конструктор з параметрами
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    @Override
    public String toString() {
        return width + "x" + height;
    }
}

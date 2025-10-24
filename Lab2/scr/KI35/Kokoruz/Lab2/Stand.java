package KI35.Kokoruz.Lab2;

/**
 * Stand — опора монітора, може регулювати висоту/нахил.
 */
public class Stand {
    private boolean heightAdjustable;
    private int minHeight;
    private int maxHeight;
    private int currentHeight;

    public Stand(boolean heightAdjustable, int minHeight, int maxHeight) {
        this.heightAdjustable = heightAdjustable;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.currentHeight = minHeight;
    }

    public Stand() {
        this(true, 0, 100);
    }

    public boolean isHeightAdjustable() { return heightAdjustable; }
    public int getCurrentHeight() { return currentHeight; }

    public void setHeight(int h) {
        if (!heightAdjustable) return;
        if (h < minHeight) currentHeight = minHeight;
        else if (h > maxHeight) currentHeight = maxHeight;
        else currentHeight = h;
    }

    @Override
    public String toString() {
        return "Stand{height=" + currentHeight + "}";
    }
}

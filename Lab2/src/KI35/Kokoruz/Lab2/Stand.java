package KI35.Kokoruz.Lab2;

/**
 * Клас Stand описує підставку монітора.
 */
public class Stand {
    private String material;

    public Stand() {
        this.material = "Plastic";
    }

    public Stand(String material) {
        this.material = material;
    }

    public String getMaterial() { return material; }

    @Override
    public String toString() {
        return material + " stand";
    }
}

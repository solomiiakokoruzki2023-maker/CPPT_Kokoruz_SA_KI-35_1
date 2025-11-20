package KI35.Kokoruz.Lab5;

import java.io.*;

/**
 * Клас FileManager реалізує методи запису та читання даних
 * у текстові та двійкові файли.
 *
 * @author Kokoruz
 * @version 1.0
 */
public class FileManager {

    /**
     * Запис у текстовий файл.
     * @param filename ім'я файлу
     * @param x значення x
     * @param y значення y
     */
    public static void writeText(String filename, double x, double y) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        out.println("x = " + x);
        out.println("y = " + y);
        out.close();
    }

    /**
     * Читання з текстового файлу.
     * @param filename ім'я файлу
     */
    public static void readText(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }

    /**
     * Запис у двійковий файл.
     * @param filename ім'я файлу
     * @param x значення x
     * @param y значення y
     */
    public static void writeBinary(String filename, double x, double y) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
        out.writeDouble(x);
        out.writeDouble(y);
        out.close();
    }

    /**
     * Читання з двійкового файлу.
     * @param filename ім'я файлу
     */
    public static void readBinary(String filename) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(filename));
        double x = in.readDouble();
        double y = in.readDouble();
        in.close();

        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }
}

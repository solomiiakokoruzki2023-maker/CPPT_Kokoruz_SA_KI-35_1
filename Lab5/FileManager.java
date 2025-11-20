package KI35.Kokoruz.Lab5;

import java.io.*;

public class FileManager {

    public static void writeText(String filename, double x, double y) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        out.println("x = " + x);
        out.println("y = " + y);
        out.close();
    }

    public static void readText(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }

    public static void writeBinary(String filename, double x, double y) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
        out.writeDouble(x);
        out.writeDouble(y);
        out.close();
    }

    public static void readBinary(String filename) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(filename));
        double x = in.readDouble();
        double y = in.readDouble();
        in.close();
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }
}

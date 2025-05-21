import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Клас за управление на файлови операции: четене и запис на продукти.
 */

public class FileManager {
    public void saveToFile(List<Product> products, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Product p : products) {
                writer.println(p.name + "|" + p.expiryDate + "|" + p.arrivalDate + "|" +
                        p.manufacturer + "|" + p.unit + "|" + p.quantity + "|" +
                        p.location.toString() + "|" + p.comment);
            }
            System.out.println("Файлът е записан.");
        } catch (IOException e) {
            System.out.println("Грешка при запис: " + e.getMessage());
        }
    }

    public List<Product> loadFromFile(String filename) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    String[] loc = parts[6].split("/");
                    Location location = new Location(loc[0], loc[1], loc[2]);
                    Product p = new Product(parts[0], parts[1], parts[2], parts[3], parts[4],
                            Double.parseDouble(parts[5]), location, parts[7]);
                    products.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Грешка при четене: " + e.getMessage());
        }
        return products;
    }
}

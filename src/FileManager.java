
import java.io.*;
import java.util.*;
import java.time.LocalDate;

/**
 * Клас за работа с файлове — запис и зареждане на продукти от файл.
 */
public class FileManager {

    /**
     * Записва списък с продукти във файл.
     * Всеки продукт се записва на отделен ред с разделител "|".
     */
    public void saveToFile(List<Product> products, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Product p : products) {
                writer.println(p.getName() + "|" + p.getExpiryDate() + "|" + p.getArrivalDate() + "|" +
                        p.getManufacturer() + "|" + p.getUnit() + "|" + p.getQuantity() + "|" +
                        p.getLocation().toString() + "|" + p.getComment());
            }
            System.out.println("Файлът е записан.");
        } catch (IOException e) {
            System.out.println("Грешка при запис: " + e.getMessage());
        }
    }

    /**
     * Зарежда списък с продукти от файл.
     * Всеки ред от файла се парсва в обект от тип Product.
     *
     * @return списък с прочетените продукти
     */
    public List<Product> loadFromFile(String filename) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    String[] loc = parts[6].split("/");
                    Location location = new Location(loc[0].trim(), loc[1].trim(), loc[2].trim());

                    Product p = new Product(
                            parts[0],
                            LocalDate.parse(parts[1]),
                            LocalDate.parse(parts[2]),
                            parts[3],
                            parts[4],
                            Double.parseDouble(parts[5]),
                            location,
                            parts[7]
                    );
                    products.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Грешка при четене: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Грешка при парсване на данни: " + e.getMessage());
        }
        return products;
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Основна бизнес логика за управление на продукти и взаимодействие със склада.
 */
public class WarehouseService {
    private List<Product> products = new ArrayList<>();
    private List<LogEntry> logs = new ArrayList<>();
    private String currentFileName;

    public void addProduct(Product product) {
        products.add(product);
        logChange("add", product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        logChange("remove", product);
    }

    public void logChange(String action, Product product) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logs.add(new LogEntry(action, product, timestamp));
    }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public List<LogEntry> getLogs() { return logs; }

    public String getCurrentFileName() { return currentFileName; }
    public void setCurrentFileName(String fileName) { this.currentFileName = fileName; }

    public void printProducts() {
        if (products.isEmpty()) {
            System.out.println("Складът е празен.");
        } else {
            for (Product p : products) {
                System.out.println("- " + p);
            }
        }
    }
    public boolean isLocationOccupied(Location location) {
        for (Product p : products) {
            if (p.location.toString().equals(location.toString())) {
                return true;
            }
        }
        return false;
    }
}

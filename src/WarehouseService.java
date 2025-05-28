
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Основна услуга за управление на складовите данни.
 * Поддържа списъци с продукти и логове на действия.
 */
public class WarehouseService {
    private List<Product> products = new ArrayList<>();
    private List<LogEntry> logs = new ArrayList<>();
    private String currentFileName;

    /**
     * Добавя продукт в склада и записва действието в логовете.
     */
    public void addProduct(Product product) {
        products.add(product);
        logChange("add", product);
    }

    /**
     * Премахва продукт от склада и записва действието в логовете.
     */
    public void removeProduct(Product product) {
        products.remove(product);
        logChange("remove", product);
    }

    /**
     * Записва лог запис за дадено действие върху продукт.
     */
    public void logChange(String action, Product product) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logs.add(new LogEntry(action, product, timestamp));
    }

    /**
     * Връща списъка с продукти.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Връща списък с всички продукти, които съвпадат по име (без значение от срока).
     *
     * @param name името на продукта за търсене
     * @return списък с продукти с даденото име (може да са с различен срок или партида)
     */
    public List<Product> findProductsByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * Задава нов списък с продукти.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Връща списъка с лог записи.
     */
    public List<LogEntry> getLogs() {
        return logs;
    }

    /**
     * Връща името на текущо отворения файл.
     */
    public String getCurrentFileName() {
        return currentFileName;
    }

    /**
     * Задава името на текущо отворения файл.
     */
    public void setCurrentFileName(String fileName) {
        this.currentFileName = fileName;
    }

    /**
     * Извежда всички продукти в склада.
     * Ако няма продукти, информира потребителя.
     */
    public void printProducts() {
        if (products.isEmpty()) {
            System.out.println("Складът е празен.");
        } else {
            for (Product p : products) {
                System.out.println("- " + p);
            }
        }
    }

    /**
     * Проверява дали дадено местоположение вече е заето от продукт.
     */
    public boolean isLocationOccupied(Location location) {
        for (Product p : products) {
            if (p.getLocation().toString().equals(location.toString())) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Клас, който описва продукт със срок на годност, количество, производител и местоположение.
 */
public class Product {
    String name;
    String expiryDate;
    String arrivalDate;
    String manufacturer;
    String unit;
    double quantity;
    Location location;
    String comment;

    public Product(String name, String expiryDate, String arrivalDate, String manufacturer,
                   String unit, double quantity, Location location, String comment) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.arrivalDate = arrivalDate;
        this.manufacturer = manufacturer;
        this.unit = unit;
        this.quantity = quantity;
        this.location = location;
        this.comment = comment;
    }

    public String getName() { return name; }
    public double getQuantity() { return quantity; }

    public String toString() {
        return name + " (" + quantity + " " + unit + "), Производител: " + manufacturer +
                ", Срок: " + expiryDate + ", Местоположение: " + location;
    }
}

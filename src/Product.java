import java.time.LocalDate;

/**
 * Клас, представляващ продукт в склада.
 * Съдържа информация като име, дати, производител, количество и местоположение.
 */
public class Product {
    private String name;
    private LocalDate expiryDate;
    private LocalDate arrivalDate;
    private String manufacturer;
    private String unit;
    private double quantity;
    private Location location;
    private String comment;

    /**
     * Създава инстанция на Product с пълна информация за продукта.
     */
    public Product(String name, LocalDate expiryDate, LocalDate arrivalDate, String manufacturer,
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



    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getUnit() {
        return unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public Location getLocation() {
        return location;
    }

    public String getComment() {
        return comment;
    }

    /**
     * Връща текстово представяне на продукта.
     */
    @Override
    public String toString() {
        return name + " (" + quantity + " " + unit + "), Производител: " + manufacturer +
                ", Срок: " + expiryDate + ", Местоположение: " + location;
    }
}

/**
 * Клас, който представя една лог записка за промяна или действие върху продукт.
 */
public class LogEntry {
    private String action;
    private Product product;
    private String timestamp;

    public LogEntry(String action, Product product, String timestamp) {
        this.action = action;
        this.product = product;
        this.timestamp = timestamp;
    }

    public String toString() {
        return timestamp + " - " + action + ": " + product;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
